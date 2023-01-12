package mikroservisneproj2.service1.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class StudentDataDto extends UserInfoDto {


    @NotEmpty
    private String indexNumber;

    private List<Integer> examsRegister = new ArrayList<>();

    public StudentDataDto() {
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public List<Integer> getExamsRegister() {
        return examsRegister;
    }

    public void setExamsRegister(List<Integer> examsRegister) {
        this.examsRegister = examsRegister;
    }
}