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
public class CourseEditBean implements Serializable {

    private final CourseDAO mCourseDAO;
    private long id;
    private Course mCourse;

    @Inject
    public CourseEditBean(CourseDAO courseDAO) {
        this.mCourseDAO = courseDAO;
    }

    public void init(){
        mCourse = mCourseDAO.findOne(id);
    }

    @ExceptionHandler
    public String update(){
        mCourseDAO.update(mCourse);
        Messages.create("The course {0} was successfully updated.", mCourse.getName()).flash().add();

        return "list.xhtml?faces-redirect=true";
    }


    @ExceptionHandler
    public String delete(){
        mCourseDAO.delete(mCourse);
        Messages.create("The course {0} was successfully deleted.", mCourse.getName()).flash().add();

        return "list.xhtml?faces-redirect=true";
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course mCourse) {
        this.mCourse = mCourse;
    }
}
