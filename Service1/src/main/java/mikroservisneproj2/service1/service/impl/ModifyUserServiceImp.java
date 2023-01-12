package mikroservisneproj2.service1.service.impl;

import io.jsonwebtoken.Claims;
import mikroservisneproj2.service1.domain.ExamRegistered;
import mikroservisneproj2.service1.domain.Student;
import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;
import mikroservisneproj2.service1.repository.ExamRegisteredRepository;
import mikroservisneproj2.service1.repository.ProfessorRepository;
import mikroservisneproj2.service1.repository.StudentRepository;
import mikroservisneproj2.service1.security.TokenService;
import mikroservisneproj2.service1.service.ModifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static mikroservisneproj2.service1.messages.ResponseMessages.*;

@Service
@Transactional
public class ModifyUserServiceImp implements ModifyUserService {

    private final StudentRepository studentRepository;

    private final ProfessorRepository professorRepository;

    private final ExamRegisteredRepository examRegisteredRepository;

    private final TokenService tokenService;


    @Autowired
    public ModifyUserServiceImp(StudentRepository studentRepository, ProfessorRepository professorRepository, ExamRegisteredRepository examRegisteredRepository, TokenService tokenService) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.examRegisteredRepository = examRegisteredRepository;
        this.tokenService = tokenService;
    }


    @Override
    public ResponseEntity<String> modifyStudent(StudentDataDto studentDataDto, String token) {


        Claims claims = this.tokenService.parseToken(token.substring(7));
        String email = (String) claims.get("email");
        System.out.println("Email: " + email);

        if (this.studentRepository.existsStudentByUserInfoEmail(email)) {

            Student student = this.studentRepository.findStudentByUserInfoEmail(email).get();

            if (studentDataDto.getIndexNumber() != null) {
                if (this.studentRepository.existsStudentByIndexNumber(studentDataDto.getIndexNumber()))
                    return ResponseEntity.status(422).body(USERNAME_TAKEN);
                student.setIndexNumber(studentDataDto.getIndexNumber());
            }
            if (studentDataDto.getEmail() != null) {
                if (this.studentRepository.existsStudentByUserInfoEmail(studentDataDto.getEmail()))
                    return ResponseEntity.status(422).body(EMAIL_TAKEN);
                student.getUserInfo().setEmail(studentDataDto.getEmail());
            }
            if (studentDataDto.getName() != null)
                student.getUserInfo().setName(studentDataDto.getName());
            if (studentDataDto.getSurname() != null)
                student.getUserInfo().setSurname(studentDataDto.getSurname());
            if (studentDataDto.getPassword() != null)
                student.getUserInfo().setPassword(studentDataDto.getPassword());
            if (studentDataDto.getPhone() != null)
                student.getUserInfo().setPhone(studentDataDto.getPhone());
            if (studentDataDto.getUsername() != null) {
                if (this.studentRepository.existsStudentByUserInfoUsername(studentDataDto.getUsername()))
                    return ResponseEntity.status(422).body(USERNAME_TAKEN);
                student.getUserInfo().setUsername(studentDataDto.getUsername());
            }
            if (studentDataDto.getBirthDate() != -1)
                student.getUserInfo().setBirthDate(studentDataDto.getBirthDate());

            if (studentDataDto.getExamsRegister() != null) {


                this.examRegisteredRepository.deleteExamRegisteredByStudent(student);
                student.getExamRegistered().clear();
                this.studentRepository.flush();
                this.examRegisteredRepository.flush();


                for (int i : studentDataDto.getExamsRegister()) {
                    ExamRegistered examRegistered = new ExamRegistered();
                    examRegistered.setStudent(student);
                    examRegistered.setExamId(i);
                    student.getExamRegistered().add(examRegistered);
                }


            }



            this.studentRepository.save(student);

        }

        return ResponseEntity.ok().body(EDIT_SUCCESS);
    }

    @Override
    public ResponseEntity<String> modifyProfessor(ProfessorDataDto professorDataDto) {
        return null;
    }
}
