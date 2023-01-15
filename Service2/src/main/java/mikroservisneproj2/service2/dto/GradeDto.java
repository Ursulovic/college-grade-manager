package mikroservisneproj2.service2.dto;

import jakarta.validation.constraints.Min;

public class GradeDto {

    private String examName;

    @Min(0)
    private int preExamPoints;

    @Min(0)
    private int examPoints;


    private int grade;

    private boolean pass;

    public GradeDto() {
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getPreExamPoints() {
        return preExamPoints;
    }

    public void setPreExamPoints(int preExamPoints) {
        this.preExamPoints = preExamPoints;
    }

    public int getExamPoints() {
        return examPoints;
    }

    public void setExamPoints(int examPoints) {
        this.examPoints = examPoints;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
