package men.okuuim.contollers;

import com.google.gson.Gson;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import men.okuuim.dto.StudentDto;
import men.okuuim.mapper.StudentDtoMapper;
import men.okuuim.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students/getAll")
    public String listBook() {
        var students = studentService.getAll();
        Gson gson = new Gson();
        ModelMapper modelMapper = new ModelMapper();
        var studentDTOs = students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
        return gson.toJson(studentDTOs);
    }

    @GetMapping("/students/delete")
    public String delete(@RequestParam("id") long id) {
        studentService.remove(id);
        return "redirect:/students/getAll";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") StudentDto user) {
        studentService.update(user.getId(), user.getFirstName());
        return "redirect:/books";
    }

    @GetMapping("/get")
    public String getBook(@RequestParam("id") long id, Model model) {
        var book = studentService.getUserBy(id);

        model.addAttribute("book", book);
        return "edit";
    }

//    @PostMapping("/books/add")
//    public String add(@ModelAttribute("book") BookDto book, Model model) {
//        userService.checkAndSave(book.getName(), book.getAuthor(),"", new String[]{book.getGenre()});
//
//        model.addAttribute("book", book);
//        return "redirect:/books";
//    }

    @GetMapping("/books/add")
    public String add() {
        return "addBook";
    }

}
