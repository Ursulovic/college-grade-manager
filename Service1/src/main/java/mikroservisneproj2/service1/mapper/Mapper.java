package mikroservisneproj2.service1.mapper;

import mikroservisneproj2.service1.domain.Student;
import mikroservisneproj2.service1.domain.UserInfo;
import mikroservisneproj2.service1.dto.StudentRegisterDto;

public class Mapper {

    public Student studentRegisterDtoToUser(StudentRegisterDto studentRegisterDto) {
        Student student = new Student();
        UserInfo userInfo = new UserInfo();

        userInfo.setEmail(studentRegisterDto.getEmail());
        userInfo.setUsername(studentRegisterDto.getUsername());
        userInfo.setPassword(studentRegisterDto.getPassword());
        userInfo.setName(studentRegisterDto.getName());
        userInfo.setSurname(studentRegisterDto.getSurname());
        userInfo.setPhone(studentRegisterDto.getPhone());
        userInfo.setBirthDate(studentRegisterDto.getBirthDate());
        student.setUserInfo(userInfo);
        student.setIndexNumber(studentRegisterDto.getIndexNumber());

        return student;
    }



}
