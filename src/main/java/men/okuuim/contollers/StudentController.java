package men.okuuim.contollers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import men.okuuim.dto.StudentDto;
import men.okuuim.service.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @ResponseBody
    @GetMapping("/student")
    public List<StudentDto> getAll() {
        return studentService.getAll();
    }

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable long id) {
        studentService.remove(id);
    }

    @PutMapping("/student")
    public void update(@RequestBody StudentDto studentDto) {
        studentService.update(studentDto);
    }

    @ResponseBody
    @GetMapping("/student/{id}")
    public StudentDto getStudents(@PathVariable long id) {
        return studentService.getStudentBy(id);
    }

    @PostMapping("/student")
    public void add(@RequestBody StudentDto studentDto) {
        studentService.save(studentDto);
    }

}
