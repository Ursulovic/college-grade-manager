package mikroservisneproj2.service2.repository;

import mikroservisneproj2.service2.domain.Exam;
import mikroservisneproj2.service2.domain.Grade;
import mikroservisneproj2.service2.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findAllByStudent(Student student);

    void deleteAllByStudentAndExam(Student student, Exam exam);

}
