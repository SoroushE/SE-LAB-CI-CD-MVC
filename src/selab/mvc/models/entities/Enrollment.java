package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.regex.Pattern;

public class Enrollment {
    private Student student;
    private Course course;
    private int points;

    public Enrollment(Student student, Course course, int points) {
        this.student = student;
        this.course = course;
        this.points = points;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public int getPoints() {
        return points;
    }
}

