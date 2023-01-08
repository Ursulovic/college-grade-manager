package mikroservisneproj2.service1.controller;

import mikroservisneproj2.service1.dto.AuthResponseDto;
import mikroservisneproj2.service1.dto.LoginRequestDto;
import mikroservisneproj2.service1.dto.StudentRegisterDto;
import mikroservisneproj2.service1.repository.AdminRepository;
import mikroservisneproj2.service1.repository.ProfessorRepository;
import mikroservisneproj2.service1.repository.StudentRepository;
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

    @PostMapping("/userRegister")
    public ResponseEntity<AuthResponseDto> studentRegister(@RequestBody @Valid StudentRegisterDto studentRegisterDto) {
        return authService.studentRegister(studentRegisterDto);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<AuthResponseDto> studentLogin(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return authService.studentLogin(loginRequestDto);
    }

// VALID NE RADI!!!!!!!!!!!!!!!!!









}
