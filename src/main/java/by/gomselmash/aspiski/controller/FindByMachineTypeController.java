package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindByMachineTypeController {

    @GetMapping("/find-by-machine-type")
    public String goFindByMachineType() {
        return "find_by_machine_type";
    }
}
