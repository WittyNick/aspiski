package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindByMachineController {

    @GetMapping("/find-by-machine")
    public String goFindByMachine() {
        return "find_by_machine";
    }
}
