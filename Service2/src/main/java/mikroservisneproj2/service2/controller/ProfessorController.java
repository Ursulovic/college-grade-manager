package mikroservisneproj2.service2.controller;

import jakarta.validation.Valid;
import mikroservisneproj2.service2.domain.Grade;
import mikroservisneproj2.service2.dto.GradeDto;
import mikroservisneproj2.service2.dto.SignGradeDto;
import mikroservisneproj2.service2.security.CheckSecurity;
import mikroservisneproj2.service2.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;
    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @PostMapping("/enterGrade")
    @CheckSecurity(roles = {"PROFESSOR"})
    public ResponseEntity<GradeDto> enterGrade(@RequestHeader("Authorization") String authorization,
                                               @RequestBody SignGradeDto signGradeDto) {
        return this.professorService.enterGrade(signGradeDto, authorization);
    }
}
