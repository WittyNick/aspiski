package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditProgramsController {

    @GetMapping("/editPrograms")
    public String goEditPrograms() {
        return "edit_programs";
    }
}
