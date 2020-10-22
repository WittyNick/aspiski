package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.WelcomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WelcomeController {
    private final WelcomeService service;

    public WelcomeController(WelcomeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String goFindByPart(Model model) {
        List<Program> programs = service.findAllProgramsSorted();
        List<Machine> machines = service.findAllMachinesSorted();
        List<ControlSystem> controlSystems = service.findAllControlSystemsSorted();
        List<Workshop> workshops = service.findAllWorkshopsSorted();
        List<Developer> developers = service.findAllDevelopersSorted();
        model
                .addAttribute("programs", programs)
                .addAttribute("machines", machines)
                .addAttribute("controlSystems", controlSystems)
                .addAttribute("workshops", workshops)
                .addAttribute("developers", developers);
        return "welcome";
    }

    @PostMapping("/programDelete")
    @ResponseBody
    public Boolean deleteProgram(@RequestBody String programId) {
        int id = Integer.parseInt(programId);
        return service.deleteProgramById(id);
    }
}
