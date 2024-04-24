package men.okuuim.repository;

import men.okuuim.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, Long> {

}
