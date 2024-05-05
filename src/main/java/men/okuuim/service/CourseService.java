package men.okuuim.service;

import java.util.List;
import men.okuuim.dto.CourseDto;

public interface CourseService {

    CourseDto getCourse(long id);
    List<CourseDto> getAll();

}
