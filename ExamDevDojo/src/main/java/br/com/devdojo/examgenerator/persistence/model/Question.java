package br.com.devdojo.examgenerator.persistence.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Question extends AbstractEntity {

    @NotEmpty(message = "The description is required")
    private String description;

    @OneToOne(optional = false)
    private Course course;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
