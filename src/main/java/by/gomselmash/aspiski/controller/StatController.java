package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.model.dto.DateRageDto;
import by.gomselmash.aspiski.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StatController {
    private final StatService service;

    public StatController(StatService service) {
        this.service = service;
    }

    @GetMapping("/stat")
    public String goStat(Model model) {
        DateRageDto dateRageNow = service.getDateRageNow();
        List<Program> programs = service.getProgramsBetweenDates(dateRageNow);
        model.addAttribute("programs", programs);
        model.addAttribute("dateRage", dateRageNow);
        return "stat";
    }

    @PostMapping("/loadPrograms")
    @ResponseBody
    public List<Program> loadPrograms(@RequestBody DateRageDto dateRageDto) {
        return service.getProgramsBetweenDates(dateRageDto);
    }
}
