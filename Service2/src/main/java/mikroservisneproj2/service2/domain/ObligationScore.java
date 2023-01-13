package mikroservisneproj2.service2.domain;

import jakarta.persistence.*;


@Entity
public class ObligationScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private PreExamObligation preExamObligation;

    private int pointsScored;

    public ObligationScore() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PreExamObligation getPreExamObligation() {
        return preExamObligation;
    }

    public void setPreExamObligation(PreExamObligation preExamObligation) {
        this.preExamObligation = preExamObligation;
    }

    public int getPointsScored() {
        return pointsScored;
    }

    public void setPointsScored(int pointsScored) {
        this.pointsScored = pointsScored;
    }
}
