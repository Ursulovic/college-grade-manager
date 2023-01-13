package mikroservisneproj2.service2.service.impl;

import mikroservisneproj2.service2.domain.Student;
import mikroservisneproj2.service2.dto.GradeDto;
import mikroservisneproj2.service2.service.StudentService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public ResponseEntity<String> registerExam(int id) {
        return null;
    }

    @Override
    public ResponseEntity<List<GradeDto>> listGrades(int id) {
        return null;
    }
}
