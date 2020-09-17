package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String goWelcome() {
        return "welcome";
    }
}
