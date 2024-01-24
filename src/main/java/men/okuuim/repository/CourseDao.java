package men.okuuim.repository;

import java.util.List;
import men.okuuim.domain.Course;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface CourseDao extends ReactiveCrudRepository<Course, Long> {

    //    Mono<Course> findBy
    @Query("SELECT * FROM course WHERE id IN (SELECT course_id FROM course_student WHERE student_id = :id)  LIMIT 10")
    Flux<Course> findCourseByStudents(Long id);

}
