package mikroservisneproj2.service2.mapper;

import mikroservisneproj2.service2.domain.Grade;
import mikroservisneproj2.service2.dto.GradeDto;

public class Mapper {

    public GradeDto gradeToGradeDto(Grade grade) {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setExamPoints(grade.getExamPoints());
        gradeDto.setPreExamPoints(grade.getPreExamPoints());
        gradeDto.setStudentId(grade.getStudent().getId());
        gradeDto.setExamName(grade.getExam().getName());
        return gradeDto;
    }
}
