package mikroservisneproj2.service2.service;

import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<String> activatePeriod(int id, String token);

    ResponseEntity<String> deactivatePeriod(int id, String token);

}
