package men.okuuim.repository;

import men.okuuim.domain.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface StudentDao extends ReactiveCrudRepository<Student, Long> {

    @Override
    Flux<Student> findAll();

    Mono<Student> findById(Student student);

    Mono<Void> deleteById(long id);

    @Query("select * from student where id = :id "
            + "join course_student on student.id = course_student.student_id "
            + "join course on course_student.course_id = course.id")
    Mono<Student> findByCourse(long id);

}
