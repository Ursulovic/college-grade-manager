package mikroservisneproj2.service2.service;

import mikroservisneproj2.service2.domain.Grade;
import mikroservisneproj2.service2.dto.GradeDto;
import mikroservisneproj2.service2.dto.SignGradeDto;
import org.springframework.http.ResponseEntity;

public interface ProfessorService {

    ResponseEntity<GradeDto> enterGrade(SignGradeDto signGradeDto, String token);
}
