package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindByProgramController {

    @GetMapping("/find-by-program")
    public String goFindByProgram() {
        return "find_by_program";
    }
}
