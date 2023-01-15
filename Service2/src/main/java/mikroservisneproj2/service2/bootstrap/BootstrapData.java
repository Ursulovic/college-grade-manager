package mikroservisneproj2.service2.bootstrap;

import mikroservisneproj2.service2.domain.*;
import mikroservisneproj2.service2.repository.ExamPeriodRepository;
import mikroservisneproj2.service2.repository.ExamRepository;
import mikroservisneproj2.service2.repository.ObligationScoreRepository;
import mikroservisneproj2.service2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final ExamPeriodRepository examPeriodRepository;

    private final ExamRepository examRepository;

    private final StudentRepository studentRepository;

    private final ObligationScoreRepository obligationScoreRepository;


    @Autowired
    public BootstrapData(ExamPeriodRepository examPeriodRepository, ExamRepository examRepository, StudentRepository studentRepository, ObligationScoreRepository obligationScoreRepository) {
        this.examPeriodRepository = examPeriodRepository;
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
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


        PreExamObligation preExamObligation1 = new PreExamObligation();
        preExamObligation1.setName("Kolokvijujm 1");
        preExamObligation1.setMaxPoints(50);
        preExamObligation1.setExam(exam1);

        Student student1 = new Student();
        student1.setEmail("ivan@gmail.com");
        student1.getExams().add(exam1);
        student1.setObligationScore(null);
        student1.setGrades(null);









        //exam1
        exam1.getPreExamObligations().add(preExamObligation1);

        this.examPeriodRepository.save(examPeriod1);
        this.examRepository.save(exam1);
        this.studentRepository.save(student1);


        System.out.println("Data loaded!");

    }
}
