package mikroservisneproj2.service2.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mikroservisneproj2.service2.domain.*;
import mikroservisneproj2.service2.dto.GradeDto;
import mikroservisneproj2.service2.dto.SignGradeDto;
import mikroservisneproj2.service2.dto.internal.OperationResultDto;
import mikroservisneproj2.service2.dto.internal.RegisterStudentExamDto;
import mikroservisneproj2.service2.mapper.Mapper;
import mikroservisneproj2.service2.repository.ExamRepository;
import mikroservisneproj2.service2.repository.GradeRepository;
import mikroservisneproj2.service2.repository.ObligationScoreRepository;
import mikroservisneproj2.service2.repository.StudentRepository;
import mikroservisneproj2.service2.security.TokenService;
import mikroservisneproj2.service2.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final GradeRepository gradeRepository;

    private final ExamRepository examRepository;

    private final StudentRepository studentRepository;

    private final ObligationScoreRepository obligationScoreRepository;

    private final Mapper mapper;

    private final TokenService tokenService;

    private final RestTemplate userServiceTemplate;

    @Autowired
    public ProfessorServiceImpl(GradeRepository gradeRepository, ExamRepository examRepository, StudentRepository studentRepository, ObligationScoreRepository obligationScoreRepository, TokenService tokenService, RestTemplate userServiceTemplate) {
        this.gradeRepository = gradeRepository;
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.obligationScoreRepository = obligationScoreRepository;
        this.tokenService = tokenService;
        this.userServiceTemplate = userServiceTemplate;
        this.mapper = new Mapper();
    }

    @Override
    public ResponseEntity<GradeDto> enterGrade(SignGradeDto signGradeDto, String token) {


        // DA li profesor predaje
        Claims claims = Jwts.claims();
        claims = tokenService.parseToken(token.substring(7));

        if (claims == null)
            return ResponseEntity.status(401).build();

        if (claims.get("exams_taught") == null)
            return ResponseEntity.status(401).build();

        List<Integer> exams_taught = (List<Integer>) claims.get("exams_taught");

        Exam e = this.examRepository.findExamsByName(signGradeDto.getExamName());

        if (!exams_taught.contains( (int) e.getId())){
            System.out.println("Exam id: " + e.getId());
            System.out.println("Examts taught: "  + exams_taught);
            return ResponseEntity.status(401).build();
        }

        // da li je student prijavio ispit

        RegisterStudentExamDto registerStudentExamDto = new RegisterStudentExamDto();
        registerStudentExamDto.setEmail(signGradeDto.getStudentEmail());
        registerStudentExamDto.setExamId((int)e.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity(registerStudentExamDto, headers);

        ResponseEntity<OperationResultDto> response = null;


        try {

            response  = this.userServiceTemplate.exchange(
                    "/internal/checkStudentExam",
                    HttpMethod.POST,
                    httpEntity,
                    OperationResultDto.class
            );

        }catch (Exception exception) {
            exception.printStackTrace();
        }

        if (!response.getBody().isSuccess()) {
            System.out.println("Student nije prijavio ispit!");
            return ResponseEntity.status(422).build();
        }
        else
            System.out.println("ISPIT PRIJAVLJEN!");



        Exam exam = this.examRepository.findExamsByName(signGradeDto.getExamName());


        if (exam == null)
            return ResponseEntity.status(422).build();

        Student student = null;


        if (this.studentRepository.findStudentByEmail(signGradeDto.getStudentEmail()).isPresent())
            student = this.studentRepository.findStudentByEmail(signGradeDto.getStudentEmail()).get();
        else
            return ResponseEntity.status(422).build();

        Random random = new Random();


        int totalPoints  = 0;
        Grade grade = new Grade();


        for (PreExamObligation preExamObligation : exam.getPreExamObligations()) {
            ObligationScore obligationScore = new ObligationScore();
            obligationScore.setStudent(student);
            obligationScore.setPreExamObligation(preExamObligation);

            int points = random.nextInt(preExamObligation.getMaxPoints()) + 1;
            totalPoints += points;
            obligationScore.setPointsScored(points);

            grade.setExam(exam);
            grade.setStudent(student);
            grade.setPreExamPoints(totalPoints);
            grade.setExamPoints(signGradeDto.getExamScore());
            grade.setGrade(getGradeFromPoints(grade.getExamPoints() + grade.getPreExamPoints()));
            grade.setDidPass(false);
            if (grade.getGrade() >= 6)
                grade.setDidPass(true);

            this.obligationScoreRepository.save(obligationScore);
            this.gradeRepository.save(grade);
        }


        return ResponseEntity.ok().body(mapper.gradeToGradeDto(grade));
    }

    int getGradeFromPoints(int points) {
        if (points >= 51 && points < 61)
            return 6;
        else if (points >= 61 && points < 71)
            return 7;
        else if (points >= 71 && points < 81)
            return 8;
        else if (points >= 81 && points < 91)
            return 9;
        else if (points >= 91)
            return 10;
        else
            return 5;
    }

}
