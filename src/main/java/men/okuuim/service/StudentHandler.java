package men.okuuim.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import men.okuuim.domain.Course;
import men.okuuim.domain.CourseStudent;
import men.okuuim.domain.Student;
import men.okuuim.dto.StudentDto;
import men.okuuim.exceptions.NotFoundException;
import men.okuuim.repository.CourseDao;
import men.okuuim.repository.CourseStudentDao;
import men.okuuim.repository.StudentDao;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

//todo ask need interface ???
@Component
@Log4j2
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final CourseStudentDao courseStudentDao;
    private final ModelMapper modelMapper;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        log.info("Trying to get students");
        Flux<StudentDto> dtoFlux = studentDao.findAll().map(m -> modelMapper.map(m, StudentDto.class))
                .switchIfEmpty(Flux.empty());

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return ServerResponse.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(dtoFlux, StudentDto.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<ServerResponse> notFound = Mono.error(new NotFoundException("User not found"));

        return ServerResponse
                .status(HttpStatus.NO_CONTENT)
                .build(studentDao.deleteById(Long.parseLong(id)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getByIdQuery(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<StudentDto> dto = studentDao.findById(Long.parseLong(id))
                .publishOn(Schedulers.boundedElastic())
                .map(s -> {
                    List<Course> courses = courseDao.findCourseByStudents(s.getId())
                            .collectList().block();
                    if (courses != null && !courses.isEmpty()) {
                        s.setCourse(courses);
                    }
                    return s;
                })
                .map(m -> modelMapper.map(m, StudentDto.class))
                .switchIfEmpty(Mono.empty());

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return ServerResponse.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(dto, StudentDto.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<StudentDto> dto = studentDao.findById(Long.parseLong(id))
                .publishOn(Schedulers.boundedElastic())
                .map(s -> {
                            List<CourseStudent> list = courseStudentDao.findAll()
                                    .collectList().block();
                            if (list != null && !list.isEmpty()) {
                                List<Course> courses = list.stream()
                                        .filter(css -> css.getStudentId().equals(s.getId()))
                                        .map(css -> courseDao
                                                .findAll()
                                                .collectList()
                                                .block()
                                                .stream()
                                                .filter(d -> d.getId().equals(css.getCourseId()))
                                                .findFirst().orElse(null))
                                        .toList();
                                s.setCourse(courses);
                            }
                            return s;
                        }
                )
                .map(m -> modelMapper.map(m, StudentDto.class))
                .switchIfEmpty(Mono.empty());

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return ServerResponse.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(dto, StudentDto.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<StudentDto> studentDtoMono = request.bodyToMono(StudentDto.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return studentDtoMono.flatMap(studentDto ->
                        ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                                .body(studentDao
                                        .save(modelMapper
                                                .map(studentDto, Student.class)), StudentDto.class))
                .switchIfEmpty(notFound);
    }


}
