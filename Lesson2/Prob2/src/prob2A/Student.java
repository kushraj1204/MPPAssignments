package prob2A;

public class Student {
    private String name;
    private GradeReport gradeReport;

    public Student(String name) {
        this.name = name;
        this.gradeReport=new GradeReport(this);
    }

    public String getName() {
        return name;
    }

    public void setGradeReport(GradeReport gradeReport) {
        this.gradeReport = gradeReport;
    }

    public GradeReport getGradeReport() {
        return gradeReport;
    }
}
