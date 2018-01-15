package br.com.devdojo.examgenerator.endpoint.v1.course;

import br.com.devdojo.examgenerator.endpoint.v1.ProfessorEndpointTest;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.repository.CourseRepository;
import br.com.devdojo.examgenerator.persistence.repository.ProfessorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CourseEndpointTest {

    @MockBean
    private CourseRepository mCourseRepository;

    @MockBean
    private ProfessorRepository mProfessorRepository;

    @Autowired
    private TestRestTemplate mTestRestTemplate;
    private HttpEntity<Void> mProfessorHeader;
    private HttpEntity<Void> mWrongHeader;
    private Course course = mockCourse();

    private static Course mockCourse(){
        return Course.CourseBuilder.newCourse()
                .id(1L)
                .name("JavaScript")
                .professor(ProfessorEndpointTest.mockProfessor())
                .build();
    }

    @Before
    public void configProfessorHeader(){
        String body = "{\"username\":\"joao\",\"password\":\"abc\"}";
        HttpHeaders headers = mTestRestTemplate.postForEntity("/login", body, String.class).getHeaders();
        mProfessorHeader = new HttpEntity<>(headers);
    }

    @Before
    public void configWrongHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","111");
        mWrongHeader = new HttpEntity<>(headers);
    }

    @Before
    public void setup(){
        BDDMockito.when(mCourseRepository.findOne(course.getId())).thenReturn(course);
        BDDMockito.when(mCourseRepository.listCourses("")).thenReturn(Collections.singletonList(course));
        BDDMockito.when(mCourseRepository.listCourses("Java")).thenReturn(Collections.singletonList(course));
    }

    @Test
    public void getCourseByIdWhenTokenIsWrongShouldReturn403() throws Exception{
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/1", HttpMethod.GET, mWrongHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 403);
    }

    @Test
    public void listCourseByIdWhenTokenIsWrongShouldReturn403() throws Exception{
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/list?name=", HttpMethod.GET, mWrongHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 403);
    }

    @Test
    public void listAllCoursesWhenNameDoesNotExistsShouldReturnEmptyList() throws Exception{
        ResponseEntity<List<Course>> exchange = mTestRestTemplate.exchange("/v1/professor/course/list?name=xaxa",
                HttpMethod.GET, mProfessorHeader, new ParameterizedTypeReference<List<Course>>() {
                });
        assertTrue(exchange.getBody().isEmpty());
    }

    @Test
    public void listAllCoursesWhenNameExistsShouldReturn200() throws Exception{
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/list?name=Java", HttpMethod.GET, mProfessorHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 200);
    }

    @Test
    public void getCourseByIdWithoutIdShouldReturn400() throws Exception{
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/", HttpMethod.GET, mProfessorHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 400);
    }

    @Test
    public void getCourseByIdWhenCourseDoesNotExistsShouldReturn404() throws Exception{
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/-1", HttpMethod.GET, mProfessorHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 404);
    }

    @Test
    public void getCourseByIdWhenCourseExistsShouldReturn200() throws Exception{
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/1", HttpMethod.GET, mProfessorHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 200);
    }

    @Test
    public void deleteCourseWhenIdExistsShouldReturn200() throws Exception{
        long id = 1L;
        BDDMockito.doNothing().when(mCourseRepository).delete(id);
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/1", HttpMethod.GET, mProfessorHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 200);
    }

    @Test
    public void deleteCourseWhenIdDoesNotExistsShouldReturn404() throws Exception{
        long id = -1L;
        BDDMockito.doNothing().when(mCourseRepository).delete(id);
        ResponseEntity<String> exchange = mTestRestTemplate.exchange("/v1/professor/course/-1", HttpMethod.GET, mProfessorHeader, String.class);
        assertTrue(exchange.getStatusCodeValue() == 404);
    }

    @Test
    public void createCourseWhenNameIsNullShouldReturn404() throws Exception{
        Course course = mCourseRepository.findOne(1L);
        course.setName(null);
        assertTrue(createCourse(course).getStatusCodeValue() == 400);
    }

    @Test
    public void createCourseWhenEverythingIsRightShouldReturn200() throws Exception{
        Course course = mCourseRepository.findOne(1L);
        assertTrue(createCourse(course).getStatusCodeValue() == 200);
    }

    private ResponseEntity<String> createCourse(Course course){
        BDDMockito.when(mCourseRepository.save(course)).thenReturn(course);
        return mTestRestTemplate.exchange("/v1/professor/course/", HttpMethod.POST, new HttpEntity<>(course, mProfessorHeader.getHeaders()), String.class);
    }
}
