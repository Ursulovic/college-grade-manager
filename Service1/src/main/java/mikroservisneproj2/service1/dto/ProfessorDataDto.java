package mikroservisneproj2.service1.dto;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDataDto extends UserInfoDto {

    private List<Integer> examsTaught = new ArrayList<>();


    public ProfessorDataDto() {
    }

    public List<Integer> getExamsTaught() {
        return examsTaught;
    }

    public void setExamsTaught(List<Integer> examsTaught) {
        this.examsTaught = examsTaught;
    }
}
