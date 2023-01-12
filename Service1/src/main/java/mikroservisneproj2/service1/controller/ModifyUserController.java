package mikroservisneproj2.service1.controller;

import mikroservisneproj2.service1.dto.StudentDataDto;
import mikroservisneproj2.service1.security.CheckSecurity;
import mikroservisneproj2.service1.service.ModifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/modify")
public class ModifyUserController {

    private final ModifyUserService modifyUserService;

    @Autowired
    public ModifyUserController(ModifyUserService modifyUserService) {
        this.modifyUserService = modifyUserService;
    }

    @PostMapping("/student")
    @CheckSecurity
    public ResponseEntity<String> modifyStudent(@RequestHeader("Authorization") String authorization,
                                                @RequestBody @Valid StudentDataDto studentDataDto) {
        return this.modifyUserService.modifyStudent(studentDataDto, authorization);
    }


}
