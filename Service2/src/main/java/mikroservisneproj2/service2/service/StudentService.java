package mikroservisneproj2.service2.service;

import mikroservisneproj2.service2.domain.Student;
import mikroservisneproj2.service2.dto.GradeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<String> registerExam(int id, String token);

    ResponseEntity<List<GradeDto>> listGrades(String token);

}
