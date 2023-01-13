package mikroservisneproj2.service2.dto;

import jakarta.validation.constraints.Min;

public class GradeDto {

    private String examName;
    private long studentId;

    private boolean didPass;

    @Min(0)
    private int preExamPoints;

    @Min(0)
    private int examPoints;

    public GradeDto() {
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public boolean isDidPass() {
        return didPass;
    }

    public void setDidPass(boolean didPass) {
        this.didPass = didPass;
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
}
