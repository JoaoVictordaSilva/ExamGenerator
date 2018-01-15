package br.com.devdojo.examgenerator.endpoint.v1.course;

import br.com.devdojo.examgenerator.endpoint.v1.AbstractEndpoint;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.repository.CourseRepository;
import br.com.devdojo.examgenerator.util.EndpointUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/professor/course")
@Api(description = "Operations related to professors")
public class CourseEndpoint extends AbstractEndpoint<Course, CourseRepository> {

    private final EndpointUtil mEndpointUtil;
    private final CourseRepository mCourseRepository;

    @Autowired
    public CourseEndpoint(CourseRepository courseRepository, EndpointUtil endpointUtil) {
        super(courseRepository);
        mCourseRepository = courseRepository;
        mEndpointUtil = endpointUtil;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCourseById(@PathVariable long id) {
        return super.getEntityById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listCourses(@ApiParam("Course name") @RequestParam(value = "name", defaultValue = "") String name) {
        return new ResponseEntity<>(mCourseRepository.listCourses(name), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return super.delete(id);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Course course) {
        return super.update(course);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Course course) {
        course.setProfessor(mEndpointUtil.extractProfessorFromToken());
        return super.create(course);
    }
}
