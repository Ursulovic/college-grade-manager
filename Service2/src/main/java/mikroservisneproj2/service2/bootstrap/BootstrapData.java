package mikroservisneproj2.service2.bootstrap;

import mikroservisneproj2.service2.domain.Exam;
import mikroservisneproj2.service2.domain.ExamPeriod;
import mikroservisneproj2.service2.domain.ObligationScore;
import mikroservisneproj2.service2.domain.PreExamObligation;
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

        Exam exam2 = new Exam();
        exam2.setName("Prog");
        exam2.setExamPeriod(examPeriod1);

        PreExamObligation preExamObligation1 = new PreExamObligation();
        preExamObligation1.setName("Kolokvijujm 1");
        preExamObligation1.setMaxPoints(50);
        preExamObligation1.setExam(exam1);



        examPeriod1.getExams().add(exam1);
        examPeriod1.getExams().add(exam2);
        exam1.setExamPeriod(examPeriod1);
        exam2.setExamPeriod(examPeriod1);







        this.examPeriodRepository.save(examPeriod1);


        System.out.println("Data loaded!");

    }
}
