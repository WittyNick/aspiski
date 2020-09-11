package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMachineTypeController {

    @GetMapping("/add-machine-type")
    public String goAddMachineType() {
        return "add_machine_type";
    }
}
