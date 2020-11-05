package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {

    @GetMapping("/logIn")
    public String goLogIn() {
        return "log_in";
    }
}
