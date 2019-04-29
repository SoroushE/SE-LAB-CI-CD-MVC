package selab.mvc.models.entities;

import org.json.JSONPropertyIgnore;
import selab.mvc.models.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    @JSONPropertyIgnore
    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        if (enrollments.isEmpty())
            return 0;
        float avg = 0;
        for (Enrollment enrollment : enrollments) {
            avg += enrollment.getPoints();
        }
        return avg / enrollments.size();
    }

    public String getCourses() {
        ArrayList<String> names = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            names.add(enrollment.getCourse().getTitle());
        }
        if (names.isEmpty())
            return "-";
        return String.join(", ", names);
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }

    public void addEnrollment(Enrollment enrollment) throws IOException {
        for (Enrollment e : enrollments) {
            if (enrollment.getCourse().equals(e.getCourse()))
                throw new IOException("Already Enrolled");
        }
        enrollments.add(enrollment);
    }

    public void removeCourse(Course course) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse() == course) {
                enrollments.remove(enrollment);
                return;
            }
        }
    }
}
