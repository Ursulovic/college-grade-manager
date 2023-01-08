package mikroservisneproj2.service1.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mikroservisneproj2.service1.domain.Student;
import mikroservisneproj2.service1.dto.AuthResponseDto;
import mikroservisneproj2.service1.dto.LoginRequestDto;
import mikroservisneproj2.service1.dto.StudentRegisterDto;
import mikroservisneproj2.service1.mapper.Mapper;
import mikroservisneproj2.service1.repository.StudentRepository;
import mikroservisneproj2.service1.security.TokenService;
import mikroservisneproj2.service1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static mikroservisneproj2.service1.messages.ResponseMessages.*;

@Service
public class AuthServiceImpl implements AuthService {

    private Mapper mapper = new Mapper();


    private final StudentRepository studentRepository;

    private final TokenService tokenService;



    @Autowired
    public AuthServiceImpl(StudentRepository studentRepository, TokenService tokenService) {
        this.studentRepository = studentRepository;
        this.tokenService = tokenService;
    }

    //POPRAVITI SISTEM PROVERE ZA EMAIL I USERNAME
    @Override
    public ResponseEntity<AuthResponseDto> studentLogin(LoginRequestDto loginRequestDto) {

        AuthResponseDto authResponseDto = new AuthResponseDto();
        int status = 200;

        authResponseDto.setJwt(null);
        authResponseDto.setMessage(LOGIN_FAIL);

        this.studentRepository.findStudentByUserInfoEmailAndUserInfoPassword(loginRequestDto.getEmail(),
                loginRequestDto.getPassword())
                .ifPresent(student -> {
                    Claims claims = Jwts.claims();
                    claims.put("id", student.getId());
                    String token = tokenService.generate(claims);
                    authResponseDto.setJwt(token);
                    authResponseDto.setMessage(LOGIN_SUCCESS);
                });



        return ResponseEntity.status(status).body(authResponseDto);
    }

    //RADI
    @Override
    public ResponseEntity<AuthResponseDto> studentRegister(StudentRegisterDto studentRegisterDto) {

        Student student = mapper.studentRegisterDtoToUser(studentRegisterDto);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        int status = 200;

        if (this.studentRepository.existsStudentByUserInfoEmail(studentRegisterDto.getEmail())) {
            authResponseDto.setJwt(null);
            authResponseDto.setMessage(REGISTER_EMAIL_TAKEN);
            status = 422;
        } else if (this.studentRepository.existsStudentByUserInfoUsername(studentRegisterDto.getUsername())) {
            authResponseDto.setJwt(null);
            authResponseDto.setMessage(REGISTER_USERNAME_TAKEN);
            status = 422;
        } else {
            Student savedStudent = this.studentRepository.save(student);

            Claims claims = Jwts.claims();

            claims.put("id", savedStudent.getId());

            String token = tokenService.generate(claims);

            authResponseDto.setJwt(token);
            authResponseDto.setMessage(REGISTER_SUCCESS);
        }

        return ResponseEntity.status(status).body(authResponseDto);


    }

    @Override
    public AuthResponseDto professorLogin() {
        return null;
    }

    @Override
    public AuthResponseDto professorRegister() {
        return null;
    }
}
