package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.WelcomeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {
    private final WelcomeService service;

    public WelcomeController(WelcomeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String goFindByPart(Model model) {
        List<Program> programs = service.findAllPrograms();
        List<Machine> machines = service.findAllMachines();
        List<ControlSystem> controlSystems = service.findAllControlSystems();
        List<Workshop> workshops = service.findAllWorkshops();
        List<Developer> developers = service.findAllDevelopers();
        model
                .addAttribute("programs", programs)
                .addAttribute("machines", machines)
                .addAttribute("controlSystems", controlSystems)
                .addAttribute("workshops", workshops)
                .addAttribute("developers", developers);
        return "welcome";
    }

    @PostMapping("/programDelete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProgram(@RequestBody String programId) {
        int id = Integer.parseInt(programId);
        service.deleteProgramById(id);
    }
}
