package men.okuuim.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import men.okuuim.domain.Student;
import men.okuuim.dto.StudentDto;
import men.okuuim.repository.StudentDao;
import men.okuuim.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto getStudentBy(long id) {
        return studentDao.findStudentById(id).map(value -> modelMapper.map(value, StudentDto.class)).orElse(null);
    }

    @Override
    public void update(StudentDto studentDto) {
        Optional<Student> optional = studentDao.findById(studentDto.getId());
        if (optional.isEmpty()) {
            log.error("No author with this credential");
            return;
        }
        modelMapper.map(studentDto, optional.get());
        studentDao.save(optional.get());
    }

    @Override
    public Student save(StudentDto studentDto) {
        return studentDao.save(modelMapper.map(studentDto, Student.class));
    }

    //todo get object not id
    @Override
    public void remove(Long id) {
        studentDao.findStudentById(id).ifPresent(studentDao::delete);
    }

    @Override
    public List<StudentDto> getAll() {
        return studentDao.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

}
