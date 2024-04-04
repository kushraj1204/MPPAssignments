package prob2A.factory;

import prob2A.GradeReport;
import prob2A.Student;

public class StudentFactory {
    public static Student createStudent(String name) {
        Student student = new Student(name);
        GradeReport report = new GradeReport(student);
        student.setGradeReport(report);
        return student;
    }
}
