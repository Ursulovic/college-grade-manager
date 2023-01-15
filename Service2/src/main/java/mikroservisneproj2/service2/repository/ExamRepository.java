package mikroservisneproj2.service2.repository;

import mikroservisneproj2.service2.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Exam findExamsByName(String name);


}
