package men.okuuim.contollers;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import men.okuuim.dto.CourseDto;
import men.okuuim.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @ResponseBody
    @GetMapping("/course/{id}")
    public CourseDto getCourse(@PathVariable long id) {
        return service.getCourse(id);
    }

    @ResponseBody
    @GetMapping("/course")
    public List<CourseDto> getAll() {
        return service.getAll();
    }

}
