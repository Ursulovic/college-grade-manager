package mikroservisneproj2.service1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String indexNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ExamRegistered> examRegistered = new ArrayList<>();

    @Embedded
    private UserInfo userInfo;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<ExamRegistered> getExamRegistered() {
        return examRegistered;
    }

    public void setExamRegistered(List<ExamRegistered> examRegistered) {
        this.examRegistered = examRegistered;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", indexNumber='" + indexNumber + '\'' +
                ", examRegistered=" + examRegistered +
                ", userInfo=" + userInfo.toString() +
                '}';
    }
}
