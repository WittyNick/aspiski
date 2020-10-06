package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.AddEditProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AddEditProgramController {
    private final AddEditProgramService service;

    public AddEditProgramController(AddEditProgramService service) {
        this.service = service;
    }

    @GetMapping("/addProgram")
    public String goAddProgram(Model model) {
        String currentDate = service.currentDate();
        model
                .addAllAttributes(getAttributeMap())
                .addAttribute("currentDate", currentDate);
        return "add_program";
    }

    @PostMapping("/editProgram")
    public String goEditProgram(@RequestParam String programId, Model model) {
        Optional<Program> optionalProgram = service.findProgramById(programId);
        if (optionalProgram.isPresent()) {
            model
                    .addAllAttributes(getAttributeMap())
                    .addAttribute("program", optionalProgram.get());
            return "edit_program";
        }
        return "redirect:/findByPart";
    }

    @PostMapping("/programSave")
    @ResponseStatus(HttpStatus.OK)
    public void saveProgram(@RequestBody Program program) {
        service.saveProgram(program);
    }

    private Map<String, Object> getAttributeMap() {
        List<Machine> machines = service.findAllMachines();
        List<ControlSystem> controlSystems = service.findAllControlSystems();
        List<Workshop> workshops = service.findAllWorkshops();
        List<Developer> developers = service.findAllDevelopers();
        Map<String, Object> map = new HashMap<>();
        map.put("machines", machines);
        map.put("controlSystems", controlSystems);
        map.put("workshops", workshops);
        map.put("developers", developers);
        return map;
    }
}