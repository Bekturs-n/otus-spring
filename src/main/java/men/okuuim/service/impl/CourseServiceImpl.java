package men.okuuim.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import men.okuuim.dto.CourseDto;
import men.okuuim.repository.CourseDao;
import men.okuuim.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public CourseDto getCourse(long id) {
        return courseDao.findById(id).map(c -> modelMapper.map(c, CourseDto.class)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> getAll() {
        return courseDao.findAll().stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .toList();
    }
}
