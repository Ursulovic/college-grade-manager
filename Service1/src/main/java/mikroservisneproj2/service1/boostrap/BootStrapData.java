package mikroservisneproj2.service1.boostrap;

import mikroservisneproj2.service1.domain.*;
import mikroservisneproj2.service1.repository.AdminRepository;
import mikroservisneproj2.service1.repository.ProfessorRepository;
import mikroservisneproj2.service1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {


    private final AdminRepository adminRepository;

    private final StudentRepository studentRepository;

    private final ProfessorRepository professorRepository;

    @Autowired
    public BootStrapData(AdminRepository adminRepository, StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Admin admin = new Admin();
        UserInfo adminInfo = new UserInfo();
        adminInfo.setName("Admin");
        adminInfo.setEmail("admin@gmail.com");
        adminInfo.setPassword("123");
        adminInfo.setSurname("Adminic");
        adminInfo.setPhone("064051212");
        adminInfo.setUsername("Adminnn");
        adminInfo.setBirthDate(1420579840);
        admin.setUserInfo(adminInfo);
        this.adminRepository.save(admin);


        Student student = new Student();
        UserInfo studentInfo = new UserInfo();
        studentInfo.setEmail("ivan@gmail.com");
        studentInfo.setName("ivan");
        studentInfo.setSurname("ursulovic");
        studentInfo.setPassword("123");
        studentInfo.setPhone("064123456");
        studentInfo.setBirthDate(1234567890);
        studentInfo.setUsername("ivan98");
        student.setExamRegistered(null);
        student.setIndexNumber("RM2220");
        student.setUserInfo(studentInfo);
        student.setExamRegistered((null));
        this.studentRepository.save(student);

        Professor professor = new Professor();
        UserInfo professorInfo = new UserInfo();
        professorInfo.setEmail("profesor@gmail.com");
        professorInfo.setName("Profesor");
        professorInfo.setSurname("profesoric");
        professorInfo.setPhone("06412345678");
        professorInfo.setPassword("123");
        professorInfo.setBirthDate(123456789);
        professorInfo.setUsername("najjaci");
        professor.setUserInfo(professorInfo);
        professor.setEmploymentDate(999876);


        ExamTaught examTaught = new ExamTaught();
        examTaught.setProfessor(professor);
        examTaught.setExamId(1);

        professor.getExamsTaught().add(examTaught);

        professorRepository.save(professor);


        System.out.println("Data loaded!");

    }
}
