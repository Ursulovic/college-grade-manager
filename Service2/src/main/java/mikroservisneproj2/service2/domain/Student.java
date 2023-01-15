package mikroservisneproj2.service2.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ObligationScore> obligationScore = new ArrayList<>();

    @OneToMany
    @JoinTable(
            name = "student_exams_listening"
    )
    private List<Exam> exams = new ArrayList<>();


    @Column(nullable = false, unique = true)
    private String email;


    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<ObligationScore> getObligationScore() {
        return obligationScore;
    }

    public void setObligationScore(List<ObligationScore> obligationScore) {
        this.obligationScore = obligationScore;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
