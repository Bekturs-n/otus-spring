package men.okuuim.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import men.okuuim.domain.Student;
import men.okuuim.repository.StudentDao;
import men.okuuim.service.StudentService;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Override
    public Optional<Student> getUserBy(long id) {
        return studentDao.findUserById(id);
    }

    @Override
    public void update(Student student) {
        Optional<Student> optionalUser = studentDao.findById(student.getId());
        if (optionalUser.isEmpty()) {
            log.error("No author with this credential");
            return;
        }

        studentDao.save(optionalUser.get());
    }

    @Override
    public void update(long id, String name) {

    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public void remove(Long id) {
        Optional<Student> student = studentDao.findUserById(id);
        student.ifPresent(studentDao::delete);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.findAll();
    }

}
