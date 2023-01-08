package mikroservisneproj2.service1.repository;

import mikroservisneproj2.service1.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    boolean existsStudentByUserInfoEmail(String email);

    boolean existsStudentByUserInfoUsername(String username);

    Optional<Student> findStudentByUserInfoEmailAndUserInfoPassword(String username, String password);

}
