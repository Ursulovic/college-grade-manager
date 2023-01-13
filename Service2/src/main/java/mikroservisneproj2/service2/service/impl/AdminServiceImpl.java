package mikroservisneproj2.service2.service.impl;

import io.jsonwebtoken.Claims;
import mikroservisneproj2.service2.repository.ExamPeriodRepository;
import mikroservisneproj2.service2.security.TokenService;
import mikroservisneproj2.service2.security.impl.TokenServiceImpl;
import mikroservisneproj2.service2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static mikroservisneproj2.service2.messages.ResponseMessages.EXAM_PERIOD_ACTIVATED;
import static mikroservisneproj2.service2.messages.ResponseMessages.EXAM_PERIOD_DEACTIVATED;

@Service
public class AdminServiceImpl implements AdminService {

    private final ExamPeriodRepository examPeriodRepository;

    private final TokenService tokenService;

    @Autowired
    public AdminServiceImpl(ExamPeriodRepository examPeriodRepository) {
        this.examPeriodRepository = examPeriodRepository;
        this.tokenService = new TokenServiceImpl();
    }


    @Override
    public ResponseEntity<String> activatePeriod(int id, String token) {


        this.examPeriodRepository.findExamPeriodById(id).ifPresent(examPeriod -> {
            examPeriod.setActive(true);
            this.examPeriodRepository.flush();
        });

        return ResponseEntity.ok().body(EXAM_PERIOD_ACTIVATED);
    }

    @Override
    public ResponseEntity<String> deactivatePeriod(int id, String token) {

        this.examPeriodRepository.findExamPeriodById(id).ifPresent(examPeriod -> {
            examPeriod.setActive(false);
            this.examPeriodRepository.flush();
        });

        return ResponseEntity.ok().body(EXAM_PERIOD_DEACTIVATED);
    }
}
