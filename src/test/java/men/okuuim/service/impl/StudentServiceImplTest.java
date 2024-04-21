package men.okuuim.service.impl;

import java.util.List;
import java.util.Optional;
import men.okuuim.domain.Student;
import men.okuuim.dto.StudentDto;
import men.okuuim.repository.StudentDao;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentDao studentDao;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private StudentServiceImpl service;

    private Student student;

    private static final Long ID = 1L;

    @BeforeEach
    public void beforeAll() {
        student = Student.builder()
                .id(ID)
                .firstName("Name")
                .lastName("Surname")
                .email("Email")
                .course(List.of())
                .enabled(true)
                .build();
    }

    @Test
    void getStudentBy() {
        when(studentDao.findStudentById(anyLong())).thenReturn(Optional.of(student));

        StudentDto studentDto = service.getStudentBy(ID);

        assertThat(studentDto).isNotNull();
        assertThat(studentDto.getId()).isEqualTo(ID);
        assertThat(studentDto.getLastName()).isEqualTo(student.getLastName());
        verify(studentDao).findStudentById(anyLong());
    }

    @Test
    @Disabled("Not ready")
    void update() {
        StudentDto studentDto = StudentDto.builder().id(ID).build();

        when(studentDao.findStudentById(anyLong())).thenReturn(Optional.of(student));
        when(studentDao.save(any())).thenReturn(student);

        service.update(studentDto);

        verify(studentDao).findStudentById(anyLong());
        verify(studentDao).save(any());
    }

    @Test
    void save() {
        StudentDto studentDto = StudentDto.builder().id(ID).build();

        when(studentDao.save(any())).thenReturn(student);

        service.save(studentDto);

        verify(studentDao).save(any());
    }

    @Test
    void remove() {
        when(studentDao.findStudentById(anyLong())).thenReturn(Optional.of(student));
        doNothing().when(studentDao).delete(student);

        service.remove(ID);

        verify(studentDao).findStudentById(anyLong());
        verify(studentDao).delete(any());
    }

    @Test
    void getAll() {
        when(studentDao.findAll()).thenReturn(List.of(student));

        List<StudentDto> list = service.getAll();

        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getLastName()).isEqualTo(student.getLastName());
    }
}