package mikroservisneproj2.service2.repository;

import mikroservisneproj2.service2.domain.ExamPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriod, Long> {

    Optional<ExamPeriod> findExamPeriodById(int id);

}
