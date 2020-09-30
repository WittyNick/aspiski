package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.AddProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddProgramController {
    private final AddProgramService addProgramService;

    public AddProgramController(AddProgramService addProgramService) {
        this.addProgramService = addProgramService;
    }

    @GetMapping("/addProgram")
    public String goAddProgram(Model model) {
        List<Machine> machines = addProgramService.findAllMachines();
        List<ControlSystem> controlSystems = addProgramService.findAllControlSystems();
        List<Workshop> workshops = addProgramService.findAllWorkshops();
        List<Developer> developers = addProgramService.findAllDevelopers();
        model
                .addAttribute("machines", machines)
                .addAttribute("controlSystems", controlSystems)
                .addAttribute("workshops", workshops)
                .addAttribute("developers", developers);
        return "add_program";
    }

    @PostMapping("/programSave")
    @ResponseStatus(HttpStatus.OK)
    public void saveProgram(@RequestBody Program program) {
        addProgramService.saveProgram(program);
    }


}
