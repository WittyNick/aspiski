package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMachineController {

    @GetMapping("/add-machine")
    public String goAddMachine() {
        return "add_machine";
    }
}
