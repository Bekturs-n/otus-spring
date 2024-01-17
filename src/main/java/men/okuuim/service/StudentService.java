package men.okuuim.service;

import java.util.List;
import men.okuuim.domain.Student;
import men.okuuim.dto.StudentDto;

public interface StudentService {

    StudentDto getStudentBy(long id);

    void update(StudentDto author);

    Student save(StudentDto studentDto);

    void remove(Long student);

    List<StudentDto> getAll();
}
