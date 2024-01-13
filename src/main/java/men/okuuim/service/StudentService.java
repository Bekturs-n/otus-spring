package men.okuuim.service;

import java.util.List;
import java.util.Optional;
import men.okuuim.domain.Student;

public interface StudentService {

    Optional<Student> getUserBy(long id);

    void update(Student author);

    void update(long id, String name);

    Student save(Student student);

    void remove(Long student);

    List<Student> getAll();
}
