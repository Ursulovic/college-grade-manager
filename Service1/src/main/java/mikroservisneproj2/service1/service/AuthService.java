package mikroservisneproj2.service1.service;

import mikroservisneproj2.service1.dto.AuthResponseDto;
import mikroservisneproj2.service1.dto.LoginRequestDto;
import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<AuthResponseDto> studentLogin(LoginRequestDto loginRequestDto);
    ResponseEntity<AuthResponseDto> studentRegister(StudentDataDto studentDataDto);

    ResponseEntity<AuthResponseDto> professorLogin(LoginRequestDto loginRequestDto);
    ResponseEntity<AuthResponseDto> professorRegister(ProfessorDataDto professorRegisterDto);

}
