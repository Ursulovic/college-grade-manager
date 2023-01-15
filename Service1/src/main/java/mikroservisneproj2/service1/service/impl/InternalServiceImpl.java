package mikroservisneproj2.service1.service.impl;

import mikroservisneproj2.service1.domain.ExamRegistered;
import mikroservisneproj2.service1.domain.Student;
import mikroservisneproj2.service1.dto.internal.OperationResultDto;
import mikroservisneproj2.service1.dto.internal.RegisterStudentExamDto;
import mikroservisneproj2.service1.repository.ExamRegisteredRepository;
import mikroservisneproj2.service1.repository.ProfessorRepository;
import mikroservisneproj2.service1.repository.StudentRepository;
import mikroservisneproj2.service1.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static mikroservisneproj2.service1.messages.ResponseMessages.*;

@Service
public class InternalServiceImpl implements InternalService {

    private final StudentRepository studentRepository;

    private final ExamRegisteredRepository examRegisteredRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public InternalServiceImpl(StudentRepository studentRepository, ExamRegisteredRepository examRegisteredRepository,
                               ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.examRegisteredRepository = examRegisteredRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public ResponseEntity<OperationResultDto> registerStudentExam(RegisterStudentExamDto registerStudentExamDto) {

        String email = registerStudentExamDto.getEmail();
        int examId = registerStudentExamDto.getExamId();
        OperationResultDto operationResultDto = new OperationResultDto();

        operationResultDto.setSuccess(false);
        operationResultDto.setMessage("");


        System.out.println("REQUEST: ");
        System.out.println(registerStudentExamDto.getEmail());
        System.out.println(registerStudentExamDto.getExamId());


        if (!this.studentRepository.existsStudentByUserInfoEmail(email)) {
            operationResultDto.setMessage(EMAIL_NOT_FOUND);
            return ResponseEntity.status(422).body(operationResultDto);
        }

        Student student1 = this.studentRepository.findStudentByUserInfoEmail(email).get();

        if (this.examRegisteredRepository.existsExamRegisteredByExamIdAndStudent(examId, student1)) {
            operationResultDto.setMessage(EXAM_REGISTER_SUCCESS);
            operationResultDto.setSuccess(true);
            return ResponseEntity.ok().body(operationResultDto);
        }

        this.studentRepository.findStudentByUserInfoEmail(email).ifPresent(student -> {
                ExamRegistered examRegistered = new ExamRegistered();
                examRegistered.setStudent(student);
                examRegistered.setExamId(examId);
                student.getExamRegistered().add(examRegistered);
                this.studentRepository.flush();

                operationResultDto.setSuccess(true);
                operationResultDto.setMessage(EXAM_REGISTER_SUCCESS);

            });


        return ResponseEntity.ok().body(operationResultDto);
    }

    @Override
    public ResponseEntity<OperationResultDto> checkStudentExam(RegisterStudentExamDto registerStudentExamDto) {

        String email = registerStudentExamDto.getEmail();
        int examId = registerStudentExamDto.getExamId();
        OperationResultDto operationResultDto = new OperationResultDto();

        operationResultDto.setMessage("");
        operationResultDto.setSuccess(false);

        if (!this.studentRepository.existsStudentByUserInfoEmail(email))
            return ResponseEntity.status(422).body(null);

        Student student = this.studentRepository.findStudentByUserInfoEmail(email).get();
        if (!this.examRegisteredRepository.existsExamRegisteredByExamIdAndStudent(examId, student)) {
            operationResultDto.setMessage(STUDENT_REGISTER_TRUE);
            operationResultDto.setSuccess(true);
        }

        return ResponseEntity.ok().body(operationResultDto);
    }
}
