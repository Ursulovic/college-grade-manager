package mikroservisneproj2.service1.service;

import mikroservisneproj2.service1.dto.AuthResponseDto;
import mikroservisneproj2.service1.dto.LoginRequestDto;
import mikroservisneproj2.service1.dto.StudentRegisterDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<AuthResponseDto> studentLogin(LoginRequestDto loginRequestDto);
    ResponseEntity<AuthResponseDto> studentRegister(StudentRegisterDto studentRegisterDto);

    AuthResponseDto professorLogin();
    AuthResponseDto professorRegister();

}
