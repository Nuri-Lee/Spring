package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")    // localhost:8080 하면 먼저 들어오는 곳
    public String home() {
        return "home";
    }
}
