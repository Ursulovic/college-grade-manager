package mikroservisneproj2.service1.service.impl;

import mikroservisneproj2.service1.repository.ProfessorRepository;
import mikroservisneproj2.service1.repository.StudentRepository;
import mikroservisneproj2.service1.service.ModifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModifyUserServiceImp implements ModifyUserService {

    private final StudentRepository studentRepository;

    private final ProfessorRepository professorRepository;


    @Autowired
    public ModifyUserServiceImp(StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public ResponseEntity modifyStudent() {



        return null;
    }

    @Override
    public ResponseEntity modifyProfessor() {
        return null;
    }
}
