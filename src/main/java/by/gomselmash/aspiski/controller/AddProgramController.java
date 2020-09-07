package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddProgramController {

    @GetMapping("/add-program")
    public String getProgram() {
        return "add_program";
    }
}
