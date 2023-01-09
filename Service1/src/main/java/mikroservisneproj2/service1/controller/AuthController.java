package mikroservisneproj2.service1.controller;

import mikroservisneproj2.service1.dto.AuthResponseDto;
import mikroservisneproj2.service1.dto.LoginRequestDto;
import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;
import mikroservisneproj2.service1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/studentRegister")
    public ResponseEntity<AuthResponseDto> studentRegister(@RequestBody @Valid StudentDataDto studentDataDto) {
        return authService.studentRegister(studentDataDto);
    }

    @PostMapping("/studentLogin")
    public ResponseEntity<AuthResponseDto> studentLogin(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return authService.studentLogin(loginRequestDto);
    }

    @PostMapping("/professorRegister")
    public ResponseEntity<AuthResponseDto> professorRegister(@RequestBody @Valid ProfessorDataDto professorRegisterDto) {
        return authService.professorRegister(professorRegisterDto);
    }

    @PostMapping("/professorLogin")
    public ResponseEntity<AuthResponseDto> professorLogin(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return authService.professorLogin(loginRequestDto);
    }

// VALID NE RADI!!!!!!!!!!!!!!!!!









}
