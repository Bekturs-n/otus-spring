package men.okuuim.repository;

import men.okuuim.domain.CourseStudent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface CourseStudentDao extends ReactiveCrudRepository<CourseStudent, Long> {

//    @Query("SELECT DISTINCT cs.id FROM CourseStudent cs")
//    Flux<CourseStudent> findAllByStudentId(Long id);

}
