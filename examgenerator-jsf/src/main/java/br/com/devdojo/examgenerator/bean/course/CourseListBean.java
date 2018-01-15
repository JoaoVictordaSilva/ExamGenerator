package br.com.devdojo.examgenerator.bean.course;

import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.model.CourseDAO;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CourseListBean implements Serializable {

    private final CourseDAO mCourseDAO;
    private List<Course> mCourseList;
    private String name = "";

    @Inject
    public CourseListBean(CourseDAO courseDAO) {
        mCourseDAO = courseDAO;
    }

    @PostConstruct
    public void init(){
        search();
    }

    public void search(){
        mCourseList = mCourseDAO.list(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return mCourseList;
    }

    public void setCourseList(List<Course> mCourseList) {
        this.mCourseList = mCourseList;
    }
}
