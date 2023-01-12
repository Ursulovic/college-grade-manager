package mikroservisneproj2.service1.repository;

import mikroservisneproj2.service1.domain.Professor;
import mikroservisneproj2.service1.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    boolean existsProfessorByUserInfoEmail(String email);

    boolean existsProfessorByUserInfoUsername(String username);

    Optional<Professor> findProfessorByUserInfoEmailAndUserInfoPassword(String email, String password);
}
