package men.okuuim.mapper;

import java.util.function.Function;
import men.okuuim.domain.Student;
import men.okuuim.dto.StudentDto;


//todo заменить на библиотеку
public class StudentDtoMapper implements Function<Student, StudentDto> {

    @Override
    public StudentDto apply(Student student) {
        return null;
//        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail());
    }

}
