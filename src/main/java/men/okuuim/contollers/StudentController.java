package men.okuuim.contollers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import men.okuuim.dto.StudentDto;
import men.okuuim.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @ResponseBody
    @GetMapping("/students/getAll")
    public List<StudentDto> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/students/delete")
    public void delete(@RequestParam("id") long id) {
        studentService.remove(id);
    }

    @PostMapping("/students/update")
    public void update(@RequestBody StudentDto studentDto) {
        studentService.update(studentDto);
    }

    @ResponseBody
    @GetMapping("/students/get")
    public StudentDto getStudents(@RequestParam("id") long id) {
        return studentService.getStudentBy(id);
    }

    @PostMapping("/students/add")
    public void add(@RequestBody StudentDto studentDto) {
        studentService.save(studentDto);
    }

}
