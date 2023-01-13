package mikroservisneproj2.service2.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ExamPeriod examPeriod;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam")
    private List<PreExamObligation> preExamObligations = new ArrayList<>();

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();





    @Column(nullable = false, unique = true)
    private String name;




    public Exam() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExamPeriod getExamPeriod() {
        return examPeriod;
    }

    public void setExamPeriod(ExamPeriod examPeriod) {
        this.examPeriod = examPeriod;
    }

    public List<PreExamObligation> getPreExamObligations() {
        return preExamObligations;
    }

    public void setPreExamObligations(List<PreExamObligation> preExamObligations) {
        this.preExamObligations = preExamObligations;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
