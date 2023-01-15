package mikroservisneproj2.service1.service;

import mikroservisneproj2.service1.dto.internal.OperationResultDto;
import mikroservisneproj2.service1.dto.internal.RegisterStudentExamDto;
import org.springframework.http.ResponseEntity;

public interface InternalService {

    ResponseEntity<OperationResultDto> registerStudentExam(RegisterStudentExamDto registerStudentExamDto);

    ResponseEntity<OperationResultDto> checkStudentExam(RegisterStudentExamDto registerStudentExamDto);
}
