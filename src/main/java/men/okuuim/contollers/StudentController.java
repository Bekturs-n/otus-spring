package men.okuuim.contollers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import men.okuuim.domain.Student;
import men.okuuim.dto.StudentDto;
import men.okuuim.repository.StudentDao;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
//@RequiredArgsConstructor
public class StudentController {
//
//    private final StudentDao studentDao;
//
//    private final ModelMapper modelMapper;
//
//    @ResponseBody
////    @GetMapping("/students/getAll")
//    public Flux<StudentDto> getAll() {
//        return null;
////        return studentDao.findAll().map(m -> modelMapper.map(m, StudentDto.class)).err;
////        return studentService.getAll();
//    }
//
//    @GetMapping("/students/delete")
//    public void delete(@RequestParam("id") long id) {
//
////        studentService.remove(id);
//    }
//
//    @PostMapping("/students/update")
//    public void update(@RequestBody StudentDto studentDto) {
//    }
//
//    @ResponseBody
//    @GetMapping("/students/get")
//    public Mono<StudentDto> getStudents(@RequestParam("id") long id) {
//        return studentDao.findById(1L).map(s -> modelMapper.map(s, StudentDto.class));
////        return null;
////        return studentService.getStudentBy(id);
//    }
//
//    @PostMapping("/students/add")
//    public void add(@RequestBody StudentDto studentDto) {
////        studentService.save(studentDto);
//    }

}
