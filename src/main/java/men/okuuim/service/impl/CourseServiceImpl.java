package men.okuuim.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import men.okuuim.dto.CourseDto;
import men.okuuim.repository.CourseDao;
import men.okuuim.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CourseDto getCourse(long id) {
        return courseDao.findById(id).map(c -> modelMapper.map(c, CourseDto.class)).orElse(null);
    }

}
