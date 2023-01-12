package mikroservisneproj2.service1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private long employmentDate;

    @Embedded
    private UserInfo userInfo;

    @OneToMany(mappedBy = "professor" , cascade = CascadeType.ALL)
    private List<ExamTaught> examsTaught = new ArrayList<>();

    public Professor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(long employmentDate) {
        this.employmentDate = employmentDate;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<ExamTaught> getExamsTaught() {
        return examsTaught;
    }

    public void setExamsTaught(List<ExamTaught> examsTaught) {
        this.examsTaught = examsTaught;
    }
}
