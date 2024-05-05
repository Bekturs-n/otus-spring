package men.okuuim.repository;

import java.util.List;
import men.okuuim.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, Long> {

    @Override
    List<Course> findAll();

}
