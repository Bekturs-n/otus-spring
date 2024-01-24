package men.okuuim.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import men.okuuim.domain.Student;
import men.okuuim.dto.StudentDto;
import men.okuuim.repository.StudentDao;
import men.okuuim.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

//todo delete if not need (ask)
@Log4j2
@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto getStudentBy(long id) {
        return null;
    }

    @Override
    public void update(StudentDto studentDto) {
    }

    @Override
    public Student save(StudentDto studentDto) {
        return null;
    }

    //todo get object not id
    @Override
    public void remove(Long id) {
    }

    @Override
    public List<StudentDto> getAll() {
        return null;
    }

}
