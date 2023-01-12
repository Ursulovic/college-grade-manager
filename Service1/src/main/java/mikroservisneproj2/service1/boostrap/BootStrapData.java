package mikroservisneproj2.service1.boostrap;

import mikroservisneproj2.service1.domain.Admin;
import mikroservisneproj2.service1.domain.ExamRegistered;
import mikroservisneproj2.service1.domain.Student;
import mikroservisneproj2.service1.domain.UserInfo;
import mikroservisneproj2.service1.repository.AdminRepository;
import mikroservisneproj2.service1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {


    private final AdminRepository adminRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public BootStrapData(AdminRepository adminRepository, StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
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
        ExamRegistered examRegistered = new ExamRegistered();
        examRegistered.setStudent(student);
        examRegistered.setExamId(1);
        ExamRegistered examRegistered2 = new ExamRegistered();
        examRegistered2.setStudent(student);
        examRegistered2.setExamId(2);
        List<ExamRegistered> examRegisteredList = new ArrayList<>();
        examRegisteredList.add(examRegistered);
        examRegisteredList.add(examRegistered2);
        student.setExamRegistered((examRegisteredList));
        this.studentRepository.save(student);


    }
}
