package br.com.devdojo.examgenerator.bean.course;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.model.CourseDAO;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CourseRegisterBean implements Serializable {

    private final CourseDAO mCourseDAO;
    private Course mCourse = new Course();

    @Inject
    public CourseRegisterBean(CourseDAO courseDAO) {
        mCourseDAO = courseDAO;

    }

    @ExceptionHandler
    public String save() {
        Course course = mCourseDAO.create(mCourse);
        Messages.create("The course {0} was successfully added.", course.getName()).flash().add();

        return "list.xhtml?faces-redirect=true";
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }
}
