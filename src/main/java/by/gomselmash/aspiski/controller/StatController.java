package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatController {
    private final StatService service;

    public StatController(StatService service) {
        this.service = service;
    }

    @GetMapping("/stat")
    public String goStat(Model model) {
        List<Program> programs = service.getProgramsThisMonth();
        model.addAttribute("programs", programs);
        return "stat";
    }
}
