package br.com.devdojo.examgenerator.persistence.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Course extends AbstractEntity {

    @NotEmpty(message = "The field name cannot be empty")
    @ApiModelProperty(notes = "The name of the course")
    private String name;

    @ManyToOne(optional = false)
    private Professor professor;

    @Column(columnDefinition = "boolean default true")
    private boolean enable = true;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static final class CourseBuilder {
        private Course course;

        private CourseBuilder() {
            course = new Course();
        }

        public static CourseBuilder newCourse() {
            return new CourseBuilder();
        }

        public CourseBuilder name(String name) {
            course.setName(name);
            return this;
        }

        public CourseBuilder id(Long id) {
            course.setId(id);
            return this;
        }

        public CourseBuilder professor(Professor professor) {
            course.setProfessor(professor);
            return this;
        }

        public Course build() {
            return course;
        }
    }
}
