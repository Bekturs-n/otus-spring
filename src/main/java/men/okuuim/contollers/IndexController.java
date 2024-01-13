package men.okuuim.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @CrossOrigin разрешение проблемы Cross-Origin Request Blocked
 * @see <a href="https://habr.com/ru/companies/macloud/articles/553826/"> </a>/>
 */
//@CrossOrigin
@RestController
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String slash() {
        return "{ \"users\": [\n"
                + "{\n"
                + "\"id\": 1,\n"
                + "\"name\": \"Name\"\n"
                + "},\n"
                + "{\n"
                + "\"id\": 2,\n"
                + "\"name\": \"NameTwo\"\n"
                + "}\n"
                + "]\n"
                + "}";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

// todo при реализации вынести в отдельные контроллеры
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/classic")
    public String classic() {
        return "on-work";
    }

    @GetMapping("/modern")
    public String modern() {
        return "on-work";
    }

    @GetMapping("/genres")
    public String genres() {
        return "on-work";
    }

    @GetMapping("/contact")
    public String contact() {
        return "on-work";
    }

    @GetMapping("/authors")
    public String authors() {
        return "on-work";
    }
}
