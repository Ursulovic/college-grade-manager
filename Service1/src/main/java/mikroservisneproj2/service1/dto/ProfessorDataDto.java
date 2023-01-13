package mikroservisneproj2.service1.dto;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDataDto extends UserInfoDto {

    private List<Integer> examsTaught = new ArrayList<>();

    private Long employmentDate;


    public ProfessorDataDto() {
    }

    public List<Integer> getExamsTaught() {
        return examsTaught;
    }

    public void setExamsTaught(List<Integer> examsTaught) {
        this.examsTaught = examsTaught;
    }

    public Long getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Long employmentDate) {
        this.employmentDate = employmentDate;
    }
}
