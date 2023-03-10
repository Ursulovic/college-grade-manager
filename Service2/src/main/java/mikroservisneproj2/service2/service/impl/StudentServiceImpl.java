package mikroservisneproj2.service2.service.impl;

import io.jsonwebtoken.Claims;
import mikroservisneproj2.service2.domain.Exam;
import mikroservisneproj2.service2.domain.ExamPeriod;
import mikroservisneproj2.service2.domain.Grade;
import mikroservisneproj2.service2.domain.Student;
import mikroservisneproj2.service2.dto.GradeDto;
import mikroservisneproj2.service2.dto.internal.OperationResultDto;
import mikroservisneproj2.service2.dto.internal.RegisterStudentExamDto;
import mikroservisneproj2.service2.mapper.Mapper;
import mikroservisneproj2.service2.repository.ExamRepository;
import mikroservisneproj2.service2.repository.GradeRepository;
import mikroservisneproj2.service2.repository.StudentRepository;
import mikroservisneproj2.service2.security.TokenService;
import mikroservisneproj2.service2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static mikroservisneproj2.service2.messages.ResponseMessages.STUDENT_EXAM_NOT_LISTENING;
import static mikroservisneproj2.service2.messages.ResponseMessages.STUDENT_EXAM_REGISTER_SUCCESS;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GradeRepository gradeRepository;

    private final ExamRepository examRepository;

    private final TokenService tokenService;

    private RestTemplate userServiceRestTemplate;

    private final Mapper mapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GradeRepository gradeRepository, ExamRepository examRepository, TokenService tokenService, RestTemplate userServiceRestTemplate) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.examRepository = examRepository;
        this.tokenService = tokenService;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.mapper = new Mapper();
    }

    @Override
    public ResponseEntity<String> registerExam(int id, String token) {

        Claims claims = this.tokenService.parseToken(token.substring(7));
        if (claims == null)
            return ResponseEntity.status(401).build();

        Exam exam1 = this.examRepository.findExamById(id);

        if (!exam1.getExamPeriod().isActive()) {
            return ResponseEntity.status(422).body("Exam is not active!");
        }

        String email = (String) claims.get("email");
        System.out.println("Email: " + email);

        Student student = this.studentRepository.findStudentByEmail(email).get();

        boolean exists = false;

        for (Exam exam : student.getExams()) {
            if (exam.getId() == id) {
                exists = true;
                break;
            }
        }

        System.out.println("AAAAAAAAAAAAAAAAAA");
        System.out.println(id);
        System.out.println(email);


        if (exists) {

            System.out.println("STUDENT IS LISTENING!");


            ResponseEntity<OperationResultDto> response = null;

            RegisterStudentExamDto registerStudentExamDto = new RegisterStudentExamDto();

            registerStudentExamDto.setEmail(email);
            registerStudentExamDto.setExamId(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            HttpEntity<RegisterStudentExamDto> httpEntity = new HttpEntity<>(registerStudentExamDto, headers);

            try {

                response = this.userServiceRestTemplate.exchange("/internal/registerStudentExam",
                        HttpMethod.PUT,
                        httpEntity,
                        OperationResultDto.class);

            }catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(response.getBody().getMessage()
            );



        } else
            return ResponseEntity.status(422).body(STUDENT_EXAM_NOT_LISTENING);





        return ResponseEntity.ok().body(STUDENT_EXAM_REGISTER_SUCCESS);
    }

    @Override
    public ResponseEntity<List<GradeDto>> listGrades(String token) {

        Claims claims = this.tokenService.parseToken(token.substring(7));
        if (claims == null)
            return ResponseEntity.status(401).build();

        String email = (String) claims.get("email");
        System.out.println("Email: " + email);

        Student student = this.studentRepository.findStudentByEmail(email).get();


        List<Grade> grades = this.gradeRepository.findAllByStudent(student);
        List<GradeDto> gradeDtos = new ArrayList<>();

        for (Grade g : grades) {
            if (g.isDidPass())
                gradeDtos.add(mapper.gradeToGradeDto(g));
        }




        return ResponseEntity.ok().body(gradeDtos);
    }
}
