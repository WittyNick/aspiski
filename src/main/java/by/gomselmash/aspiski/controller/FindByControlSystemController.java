package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindByControlSystemController {

    @GetMapping("/find-by-control-system")
    public String goFindByControlSystem() {
        return "find_by_control_system";
    }
}
