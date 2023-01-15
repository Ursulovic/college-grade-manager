package mikroservisneproj2.service1.controller;

import mikroservisneproj2.service1.dto.internal.OperationResultDto;
import mikroservisneproj2.service1.dto.internal.RegisterStudentExamDto;
import mikroservisneproj2.service1.security.CheckSecurity;
import mikroservisneproj2.service1.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/internal")
public class InternalController {

    private final InternalService internalService;

    @Autowired
    public InternalController(InternalService internalService) {
        this.internalService = internalService;
    }

    @PutMapping("/registerStudentExam")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<OperationResultDto> registerExam(@RequestHeader("Authorization") String authorization
            , @RequestBody @Valid RegisterStudentExamDto registerStudentExamDto) {
        return internalService.registerStudentExam(registerStudentExamDto);
    }


    @PostMapping("/checkStudentExam")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<OperationResultDto> checkStudentExam(@RequestHeader("Authorization") String authorization
            , @RequestBody @Valid RegisterStudentExamDto registerStudentExamDto) {
        return internalService.checkStudentExam(registerStudentExamDto);
    }

}

