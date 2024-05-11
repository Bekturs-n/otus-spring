package men.okuuim.repository;

import men.okuuim.domain.CourseStudent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CourseStudentDao extends ReactiveCrudRepository<CourseStudent, Long> {

}
