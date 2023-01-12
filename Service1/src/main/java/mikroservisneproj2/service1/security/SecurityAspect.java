package mikroservisneproj2.service1.security;

import io.jsonwebtoken.Claims;
import mikroservisneproj2.service1.dto.StudentDataDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class SecurityAspect {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    private final TokenService tokenService;

    @Autowired
    public SecurityAspect(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Around("@annotation(mikroservisneproj2.service1.security.CheckSecurity)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        String token = null;


        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equals("authorization")) {
                //Check bearer schema
                if (joinPoint.getArgs()[i].toString().startsWith("Bearer")) {
                    //Get token
                    token = joinPoint.getArgs()[i].toString().split(" ")[1];
                }
            }
        }


        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Claims claims = tokenService.parseToken(token);

        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }






        return  joinPoint.proceed();

    }










}
