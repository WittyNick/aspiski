package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindByControlSystemController {

    @GetMapping("/findByControlSystem")
    public String goFindByControlSystem() {
        return "find_by_control_system";
    }
}
