package mikroservisneproj2.service2.dto.internal;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterStudentExamDto {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private int examId;

    public RegisterStudentExamDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }
}
