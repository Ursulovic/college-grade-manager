package mikroservisneproj2.service1.repository;

import mikroservisneproj2.service1.domain.ExamRegistered;
import mikroservisneproj2.service1.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRegisteredRepository extends JpaRepository<ExamRegistered, Long> {

    void deleteExamRegisteredByStudent(Student student);
}
