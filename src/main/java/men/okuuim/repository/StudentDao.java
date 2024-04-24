package men.okuuim.repository;

import java.util.List;
import java.util.Optional;
import men.okuuim.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Long> {

    @Override
    List<Student> findAll();

    Optional<Student> findStudentById(long id);

}
