package br.com.devdojo.examgenerator.persistence.model;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.custom.CustomTypeReference;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class CourseDAO implements Serializable {

    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/{id}";
    private final String CREATE_OR_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/";
    private final CustomRestTemplate mCustomRestTemplate;
    private final JsonUtil mJsonUtil;
    private final CustomTypeReference<List<Course>> mListCourse;

    @Inject
    public CourseDAO(CustomRestTemplate customRestTemplate, JsonUtil jsonUtil, CustomTypeReference<List<Course>> listCustomTypeReference) {
        mCustomRestTemplate = customRestTemplate;
        mJsonUtil = jsonUtil;
        mListCourse = listCustomTypeReference;
    }

    @ExceptionHandler
    public List<Course> list(String name) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        ResponseEntity<List<Course>> listResponseEntity = mCustomRestTemplate.exchange(url.toString(), HttpMethod.GET, mJsonUtil.tokenizedHttpEntityHeader(), mListCourse.typeReference());

        return listResponseEntity.getBody();
    }

    @ExceptionHandler
    public Course findOne(long id) {
        return mCustomRestTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.GET, mJsonUtil.tokenizedHttpEntityHeader(), Course.class, id).getBody();
    }

    public Course update(Course course) {
        return createOrUpdate(HttpMethod.PUT, course);
    }

    public Course create(Course course) {
        return createOrUpdate(HttpMethod.POST, course);
    }

    public void delete(Course course) {
        mCustomRestTemplate.exchange(DELETE_OR_FIND_ONE_URL, HttpMethod.DELETE, mJsonUtil.tokenizedHttpEntityHeader(), Course.class, course.getId());
    }

    private Course createOrUpdate(HttpMethod httpMethod, Course course) {
        return mCustomRestTemplate.exchange(CREATE_OR_UPDATE_URL, httpMethod, mJsonUtil.tokenizedHttpEntityHeader(course), Course.class).getBody();

    }
}
