package men.okuuim.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import men.okuuim.domain.Course;
import men.okuuim.repository.CourseDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseDao courseDao;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CourseServiceImpl subj;

    private Course course;

    @BeforeEach
    public void setUp() {
        course = Course.builder()
                .id(1L)
                .name("Course")
                .students(new ArrayList<>())
                .build();
    }

    @Test
    void getCourse() {
        when(courseDao.findById(anyLong())).thenReturn(Optional.of(course));

        subj.getCourse(anyLong());

        verify(courseDao).findById(anyLong());
    }

    @Test
    void getAll() {
        when(courseDao.findAll()).thenReturn(Arrays.asList(course));

        subj.getAll();

        verify(courseDao).findAll();
    }

}