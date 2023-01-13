package mikroservisneproj2.service2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class PreExamObligation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Exam exam;


    @Column(nullable = false)
    private String name;

    @Min(0)
    @Column(nullable = false)
    private int MaxPoints;



    public PreExamObligation() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPoints() {
        return MaxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        MaxPoints = maxPoints;
    }

}
