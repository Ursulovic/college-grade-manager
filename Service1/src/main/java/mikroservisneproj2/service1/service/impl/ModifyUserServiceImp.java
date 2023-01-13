package mikroservisneproj2.service1.service.impl;

import io.jsonwebtoken.Claims;
import mikroservisneproj2.service1.domain.*;
import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;
import mikroservisneproj2.service1.dto.UserInfoDto;
import mikroservisneproj2.service1.repository.*;
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

    private final AdminRepository adminRepository;

    private final ExamRegisteredRepository examRegisteredRepository;

    private final ExamsTaughtRepository examsTaughtRepository;

    private final TokenService tokenService;


    @Autowired
    public ModifyUserServiceImp(StudentRepository studentRepository, ProfessorRepository professorRepository, AdminRepository adminRepository, ExamRegisteredRepository examRegisteredRepository, ExamsTaughtRepository examsTaughtRepository, TokenService tokenService) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.adminRepository = adminRepository;
        this.examRegisteredRepository = examRegisteredRepository;
        this.examsTaughtRepository = examsTaughtRepository;
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
            if (studentDataDto.getBirthDate() != null)
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
            return ResponseEntity.ok().body(EDIT_SUCCESS);


        }

        return ResponseEntity.status(401).build();
    }

    @Override
    public ResponseEntity<String> modifyProfessor(ProfessorDataDto professorDataDto, String token) {

        Claims claims = this.tokenService.parseToken(token.substring(7));
        String email = (String) claims.get("email");
        System.out.println("Email: " + email);

        if (this.professorRepository.existsProfessorByUserInfoEmail(email)) {
            Professor professor = this.professorRepository.findProfessorByUserInfoEmail(email).get();

            if (professorDataDto.getEmail() != null) {
                if (this.professorRepository.existsProfessorByUserInfoEmail(professorDataDto.getEmail())) {
                    return ResponseEntity.status(422).body(EMAIL_TAKEN);
                }
                professor.getUserInfo().setEmail(professorDataDto.getEmail());
            }
            if (professorDataDto.getUsername() != null) {
                if (this.professorRepository.existsProfessorByUserInfoUsername(professorDataDto.getUsername())) {
                    return ResponseEntity.status(422).body(USERNAME_TAKEN);
                }
                professor.getUserInfo().setUsername(professorDataDto.getUsername());
            }

            if (professorDataDto.getName() != null)
                professor.getUserInfo().setName(professorDataDto.getName());
            if (professorDataDto.getSurname() != null)
                professor.getUserInfo().setSurname(professorDataDto.getSurname());
            if (professorDataDto.getPassword() != null)
                professor.getUserInfo().setPassword(professorDataDto.getPassword());
            if (professorDataDto.getBirthDate() != null)
                professor.getUserInfo().setBirthDate(professorDataDto.getBirthDate());
            if (professorDataDto.getPhone() != null)
                professor.getUserInfo().setPhone(professorDataDto.getPhone());
            if (professorDataDto.getEmploymentDate() != null)
                professor.setEmploymentDate(professorDataDto.getEmploymentDate());
            if (professorDataDto.getExamsTaught() != null) {

                this.examsTaughtRepository.deleteExamTaughtByProfessor(professor);
                professor.getExamsTaught().clear();
                this.professorRepository.flush();
                this.examsTaughtRepository.flush();

                for (int i : professorDataDto.getExamsTaught()) {
                    ExamTaught examTaught = new ExamTaught();
                    examTaught.setProfessor(professor);
                    examTaught.setExamId(i);
                    professor.getExamsTaught().add(examTaught);
                }
            }

            this.professorRepository.save(professor);

            return ResponseEntity.ok().body(EDIT_SUCCESS);
        }


        return ResponseEntity.status(401).build();
    }


    public ResponseEntity<String> modifyAdmin(UserInfoDto userInfoDto, String token) {

        Claims claims = this.tokenService.parseToken(token.substring(7));
        String email = (String) claims.get("email");
        System.out.println("Email: " + email);


        if (this.adminRepository.existsAdminByUserInfoEmail(email)) {

            Admin admin = this.adminRepository.findAdminByUserInfoEmail(email).get();

            if (userInfoDto.getEmail() != null) {
                if (this.adminRepository.existsAdminByUserInfoEmail(userInfoDto.getEmail()))
                    return ResponseEntity.status(422).body(EMAIL_TAKEN);
                admin.getUserInfo().setEmail(userInfoDto.getEmail());
            }
            if (userInfoDto.getUsername() != null) {
                if (this.adminRepository.existsAdminByUserInfoUsername(userInfoDto.getUsername()))
                    return ResponseEntity.status(422).body(USERNAME_TAKEN);
                admin.getUserInfo().setUsername(userInfoDto.getUsername());
            }
            if (userInfoDto.getName() != null)
                admin.getUserInfo().setName(userInfoDto.getName());
            if (userInfoDto.getSurname() != null)
                admin.getUserInfo().setSurname(userInfoDto.getSurname());
            if (userInfoDto.getPassword() != null)
                admin.getUserInfo().setPassword(userInfoDto.getPassword());
            if (userInfoDto.getBirthDate() != null)
                admin.getUserInfo().setBirthDate(userInfoDto.getBirthDate());
            if (userInfoDto.getPhone() != null)
                admin.getUserInfo().setPhone(userInfoDto.getPhone());

            this.adminRepository.save(admin);
            return ResponseEntity.ok().body(EDIT_SUCCESS);
        }


        return ResponseEntity.status(422).build();
    }





}
