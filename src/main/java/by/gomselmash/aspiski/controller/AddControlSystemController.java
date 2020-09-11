package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddControlSystemController {

    @GetMapping("/add-control-system")
    public String goAddControlSystem() {
        return "add_control_system";
    }
}
