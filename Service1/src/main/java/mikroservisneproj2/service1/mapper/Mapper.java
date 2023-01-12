package mikroservisneproj2.service1.mapper;

import mikroservisneproj2.service1.domain.*;
import mikroservisneproj2.service1.dto.ProfessorDataDto;
import mikroservisneproj2.service1.dto.StudentDataDto;

import java.util.Date;

public class Mapper {

    public Student studentRegisterDtoToUser(StudentDataDto studentDataDto) {
        Student student = new Student();
        UserInfo userInfo = new UserInfo();

        userInfo.setEmail(studentDataDto.getEmail());
        userInfo.setUsername(studentDataDto.getUsername());
        userInfo.setPassword(studentDataDto.getPassword());
        userInfo.setName(studentDataDto.getName());
        userInfo.setSurname(studentDataDto.getSurname());
        userInfo.setPhone(studentDataDto.getPhone());
        userInfo.setBirthDate(studentDataDto.getBirthDate());
        student.setUserInfo(userInfo);
        student.setIndexNumber(studentDataDto.getIndexNumber());

        for (int i : studentDataDto.getExamsRegister()) {
            ExamRegistered examRegistered = new ExamRegistered();
            examRegistered.setExamId(i);
            examRegistered.setStudent(student);
            student.getExamRegistered().add(examRegistered);
        }


        return student;
    }

    public Professor professorRegisterDtotoProfessor(ProfessorDataDto professorRegisterDto) {
        Professor professor = new Professor();
        UserInfo userInfo = new UserInfo();

        userInfo.setEmail(professorRegisterDto.getEmail());
        userInfo.setUsername(professorRegisterDto.getUsername());
        userInfo.setPassword(professorRegisterDto.getPassword());
        userInfo.setName(professorRegisterDto.getName());
        userInfo.setSurname(professorRegisterDto.getSurname());
        userInfo.setPhone(professorRegisterDto.getPhone());
        userInfo.setBirthDate(professorRegisterDto.getBirthDate());
        professor.setUserInfo(userInfo);

        Date date = new Date();
        professor.setEmploymentDate(date.getTime());

        for (int i : professorRegisterDto.getExamsTaught()) {
            ExamTaught examTaught = new ExamTaught();
            examTaught.setProfessor(professor);
            examTaught.setExamId(i);
            professor.getExamsTaught().add(examTaught);
        }

        return professor;
    }



}
