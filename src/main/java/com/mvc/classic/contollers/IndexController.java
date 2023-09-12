package com.mvc.classic.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String slash() {
        return "redirect:/index";
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
