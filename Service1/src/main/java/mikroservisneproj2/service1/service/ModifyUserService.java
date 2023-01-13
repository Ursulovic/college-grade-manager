package mikroservisneproj2.service1.service;

import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;
import mikroservisneproj2.service1.dto.UserInfoDto;
import org.springframework.http.ResponseEntity;

public interface ModifyUserService {

    ResponseEntity<String> modifyStudent(StudentDataDto studentDataDto, String token);

    ResponseEntity<String> modifyProfessor(ProfessorDataDto professorDataDto, String token);

    ResponseEntity<String> modifyAdmin(UserInfoDto userInfoDto, String token);


}
