package mikroservisneproj2.service2.controller;

import mikroservisneproj2.service2.security.CheckSecurity;
import mikroservisneproj2.service2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PatchMapping("/activate/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<String> activateExamPeriod(@RequestHeader("Authorization") String authorization,
                                                     @PathVariable("id") int id) {
        return this.adminService.activatePeriod(id, authorization);
    }

    @PatchMapping("/deactivate/{id}")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<String> deactivateExamPeriod(@RequestHeader("Authorization") String authorization,
                                                     @PathVariable("id") int id) {
        return this.adminService.deactivatePeriod(id, authorization);
    }

}
