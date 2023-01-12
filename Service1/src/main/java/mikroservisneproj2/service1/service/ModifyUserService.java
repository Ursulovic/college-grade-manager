package mikroservisneproj2.service1.service;

import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;
import org.springframework.http.ResponseEntity;

public interface ModifyUserService {

    ResponseEntity<String> modifyStudent(StudentDataDto studentDataDto, String token);

    ResponseEntity<String> modifyProfessor(ProfessorDataDto professorDataDto);


}
