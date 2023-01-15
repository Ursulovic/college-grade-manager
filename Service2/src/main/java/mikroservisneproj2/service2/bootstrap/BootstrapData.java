package mikroservisneproj2.service2.bootstrap;

import mikroservisneproj2.service2.domain.*;
import mikroservisneproj2.service2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final ExamPeriodRepository examPeriodRepository;

    private final ExamRepository examRepository;

    private final StudentRepository studentRepository;

    private final GradeRepository gradeRepository;

    private final ObligationScoreRepository obligationScoreRepository;


    @Autowired
    public BootstrapData(ExamPeriodRepository examPeriodRepository, ExamRepository examRepository, StudentRepository studentRepository, GradeRepository gradeRepository, ObligationScoreRepository obligationScoreRepository) {
        this.examPeriodRepository = examPeriodRepository;
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.obligationScoreRepository = obligationScoreRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        ExamPeriod examPeriod1 = new ExamPeriod();
        examPeriod1.setName("Januar");
        examPeriod1.setActive(false);

        Exam exam1 = new Exam();
        exam1.setName("Mata");
        exam1.setExamPeriod(examPeriod1);

        Exam exam2 = new Exam();
        exam2.setName("Programiranje");
        exam2.setExamPeriod(examPeriod1);




        PreExamObligation preExamObligation1 = new PreExamObligation();
        preExamObligation1.setName("Kolokvijujm 1");
        preExamObligation1.setMaxPoints(50);
        preExamObligation1.setExam(exam1);

        PreExamObligation preExamObligation2 = new PreExamObligation();
        preExamObligation2.setName("Projekat 1");
        preExamObligation2.setExam(exam1);
        preExamObligation2.setMaxPoints(30);


        Student student1 = new Student();
        student1.setEmail("ivan@gmail.com");
        student1.getExams().add(exam1);
        student1.getExams().add(exam2);
        student1.setObligationScore(null);


//        Grade grade = new Grade();
//        grade.setStudent(student1);
//        grade.setPreExamPoints(33);
//        grade.setExamPoints(44);
//        grade.setGrade(8);
//        grade.setExam(exam1);
//        grade.setDidPass(true);





        //student
//        student1.getGrades().add(grade);


        //exam1
        exam1.getPreExamObligations().add(preExamObligation1);


        //exam2
        exam2.getPreExamObligations().add(preExamObligation2);

        this.examPeriodRepository.save(examPeriod1);
        this.examRepository.save(exam1);
        this.examRepository.save(exam2);
        this.studentRepository.save(student1);


        System.out.println("Data loaded!");

    }
}
