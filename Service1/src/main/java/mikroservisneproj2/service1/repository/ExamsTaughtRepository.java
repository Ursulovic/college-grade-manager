package mikroservisneproj2.service1.repository;

import mikroservisneproj2.service1.domain.ExamTaught;
import mikroservisneproj2.service1.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamsTaughtRepository extends JpaRepository<ExamTaught, Long> {

    void deleteExamTaughtByProfessor(Professor professor);
}
