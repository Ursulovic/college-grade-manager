package mikroservisneproj2.service2.controller;

import mikroservisneproj2.service2.dto.GradeDto;
import mikroservisneproj2.service2.security.CheckSecurity;
import mikroservisneproj2.service2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/registerExam/{id}")
    @CheckSecurity(roles = {"STUDENT"})
    public ResponseEntity<String> registerExam(@RequestHeader("Authorization") String authorization, @PathVariable("id") int id) {
        return studentService.registerExam(id, authorization);
    }

    @GetMapping("/listGrades")
    @CheckSecurity(roles = {"STUDENT"})
    public ResponseEntity<List<GradeDto>> listGrades(@RequestHeader("Authorization") String authorization) {
        return studentService.listGrades(authorization);
    }

}
