package mikroservisneproj2.service1.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mikroservisneproj2.service1.domain.ExamRegistered;
import mikroservisneproj2.service1.domain.ExamTaught;
import mikroservisneproj2.service1.domain.Professor;
import mikroservisneproj2.service1.domain.Student;
import mikroservisneproj2.service1.dto.AuthResponseDto;
import mikroservisneproj2.service1.dto.LoginRequestDto;
import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;
import mikroservisneproj2.service1.mapper.Mapper;
import mikroservisneproj2.service1.repository.ExamRegisteredRepository;
import mikroservisneproj2.service1.repository.ExamsTaughtRepository;
import mikroservisneproj2.service1.repository.ProfessorRepository;
import mikroservisneproj2.service1.repository.StudentRepository;
import mikroservisneproj2.service1.security.TokenService;
import mikroservisneproj2.service1.service.AuthService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static mikroservisneproj2.service1.messages.ResponseMessages.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final Mapper mapper;


    private final StudentRepository studentRepository;

    private final ProfessorRepository professorRepository;

    private final ExamRegisteredRepository examRegisteredRepository;

    private final ExamsTaughtRepository examsTaughtRepository;

    private final TokenService tokenService;

    Session session;




    @Autowired
    public AuthServiceImpl(StudentRepository studentRepository, ProfessorRepository professorRepository, ExamRegisteredRepository examRegisteredRepository, ExamsTaughtRepository examsTaughtRepository, TokenService tokenService) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.examRegisteredRepository = examRegisteredRepository;
        this.examsTaughtRepository = examsTaughtRepository;
        this.tokenService = tokenService;
        this.mapper = new Mapper();
    }

    @Override
    public ResponseEntity<AuthResponseDto> studentLogin(LoginRequestDto loginRequestDto) {

        AuthResponseDto authResponseDto = new AuthResponseDto();
        int status = 401;

        authResponseDto.setJwt(null);
        authResponseDto.setMessage(LOGIN_FAIL);

        this.studentRepository.findStudentByUserInfoEmailAndUserInfoPassword(loginRequestDto.getEmail(),
                loginRequestDto.getPassword())
                .ifPresent(student -> {
                    Claims claims = Jwts.claims();
                    claims.put("id", student.getId());
                    claims.put("role", "STUDENT");
                    claims.put("exams_registered", student.getExamRegistered());
                    String token = tokenService.generate(claims);
                    authResponseDto.setJwt(token);
                    authResponseDto.setMessage(LOGIN_SUCCESS);
                });

        if (this.studentRepository.findStudentByUserInfoEmailAndUserInfoPassword(loginRequestDto.getEmail(),
                loginRequestDto.getPassword()).isPresent()){ status = 200;}


        return ResponseEntity.status(status).body(authResponseDto);
    }

    //RADI
    @Override
    public ResponseEntity<AuthResponseDto> studentRegister(StudentDataDto studentDataDto) {

        Student student = mapper.studentRegisterDtoToUser(studentDataDto);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        int status = 422;

        if (this.studentRepository.existsStudentByUserInfoEmail(studentDataDto.getEmail())) {
            authResponseDto.setJwt(null);
            authResponseDto.setMessage(REGISTER_EMAIL_TAKEN);
        } else if (this.studentRepository.existsStudentByUserInfoUsername(studentDataDto.getUsername())) {
            authResponseDto.setJwt(null);
            authResponseDto.setMessage(REGISTER_USERNAME_TAKEN);
        } else {
            Student savedStudent = this.studentRepository.save(student);

            for (int i : studentDataDto.getExamsRegister()) {
                ExamRegistered examRegistered = new ExamRegistered();
                examRegistered.setExamId(i);
                examRegistered.setStudent(savedStudent);
                this.examRegisteredRepository.save(examRegistered);
            }


            Claims claims = Jwts.claims();
            claims.put("id", savedStudent.getId());
            claims.put("role", "STUDENT");
            claims.put("exams_registered", studentDataDto.getExamsRegister());

            String token = tokenService.generate(claims);

            status = 200;

            authResponseDto.setJwt(token);
            authResponseDto.setMessage(REGISTER_SUCCESS);
        }

        return ResponseEntity.status(status).body(authResponseDto);


    }

    @Override
    public ResponseEntity<AuthResponseDto> professorLogin(LoginRequestDto loginRequestDto) {

        AuthResponseDto authResponseDto = new AuthResponseDto();
        int status = 401;

        authResponseDto.setJwt(null);
        authResponseDto.setMessage(LOGIN_FAIL);

        this.professorRepository.findProfessorByUserInfoEmailAndUserInfoPassword(loginRequestDto.getEmail(),
                loginRequestDto.getPassword()).ifPresent(professor -> {
                    Claims claims = Jwts.claims();
                    claims.put("id", professor.getId());
                    claims.put("role", "PROFESSOR");
                    claims.put("exams_taught", professor.getExamsTaught());
                    String token = tokenService.generate(claims);
                    authResponseDto.setJwt(token);
                    authResponseDto.setMessage(LOGIN_SUCCESS);
        });

        if (this.professorRepository.findProfessorByUserInfoEmailAndUserInfoPassword(loginRequestDto.getEmail(),
                loginRequestDto.getPassword()).isPresent()) { status = 200; }



        return ResponseEntity.status(status).body(authResponseDto);
    }

    @Override
    public ResponseEntity<AuthResponseDto> professorRegister(ProfessorDataDto professorRegisterDto) {

        Professor professor = mapper.professorRegisterDtotoProfessor(professorRegisterDto);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        int status = 422;

        if (this.professorRepository.existsProfessorByUserInfoEmail(professorRegisterDto.getEmail())) {
            authResponseDto.setJwt(null);
            authResponseDto.setMessage(REGISTER_EMAIL_TAKEN);
        } else if (this.professorRepository.existsProfessorByUserInfoUsername(professorRegisterDto.getUsername())) {
            authResponseDto.setJwt(null);
            authResponseDto.setMessage(REGISTER_USERNAME_TAKEN);
        } else {
            Professor savedProfessor = this.professorRepository.save(professor);


            for (int i : professorRegisterDto.getExamsTaught()) {
                ExamTaught examTaught = new ExamTaught();
                examTaught.setProfessor(savedProfessor);
                examTaught.setExamId(i);
                this.examsTaughtRepository.save(examTaught);
            }


            Claims claims = Jwts.claims();
            claims.put("id", savedProfessor.getId());
            claims.put("role", "PROFESSOR");
            claims.put("exams_taught", professorRegisterDto.getExamsTaught());
            String token = tokenService.generate(claims);
            status = 200;

            authResponseDto.setJwt(token);
            authResponseDto.setMessage(REGISTER_SUCCESS);


        }


        return ResponseEntity.status(status).body(authResponseDto);
    }
}
