package br.com.devdojo.examgenerator.persistence.repository;

import br.com.devdojo.examgenerator.persistence.model.Course;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SuppressWarnings("ALL")
public interface CourseRepository extends CustomPagingAndSortRepository<Course, Long> {

    @Query("select c from Course c where c.name like %?1% and c.professor = ?#{principal.professor} and c.enable = true")
    List<Course> listCourses(String name);

}
