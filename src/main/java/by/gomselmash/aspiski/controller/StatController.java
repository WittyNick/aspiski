package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatController {

    @GetMapping("/stat")
    public String goStat() {
        return "stat";
    }
}
