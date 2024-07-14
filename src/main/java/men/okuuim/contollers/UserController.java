package men.okuuim.contollers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import men.okuuim.dto.UserDto;
import men.okuuim.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//todo rename url from student to user
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping("/student")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable long id) {
        userService.remove(id);
    }

    @PutMapping("/student")
    public void update(@RequestBody UserDto userDto) {
        userService.update(userDto);
    }

    @ResponseBody
    @GetMapping("/student/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUserBy(id);
    }

    @PostMapping("/student")
    public void add(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

}
