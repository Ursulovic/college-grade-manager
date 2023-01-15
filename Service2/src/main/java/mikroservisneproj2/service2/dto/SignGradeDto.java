package mikroservisneproj2.service2.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class SignGradeDto {

    @NotEmpty
    private int examScore;

    @NotEmpty
    private String examName;

    @NotEmpty
    private String studentEmail;

    public SignGradeDto() {
    }

    public int getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
