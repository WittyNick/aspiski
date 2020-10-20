package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.AddEditProgramService;
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
        return "redirect:/";
    }

    @PostMapping("/programSave")
    @ResponseBody
    public Boolean saveProgram(@RequestBody Program program) {
        return service.saveProgram(program);
    }

    @PostMapping("/programUpdate")
    @ResponseBody
    public Boolean updateProgram(@RequestBody Program program) {
        return service.updateProgram(program);
    }

    private Map<String, Object> getAttributeMap() {
        List<Machine> machines = service.findAllMachinesSorted();
        List<ControlSystem> controlSystems = service.findAllControlSystemsSorted();
        List<Workshop> workshops = service.findAllWorkshopsSorted();
        List<Developer> developers = service.findAllDevelopersSorted();
        Map<String, Object> map = new HashMap<>();
        map.put("machines", machines);
        map.put("controlSystems", controlSystems);
        map.put("workshops", workshops);
        map.put("developers", developers);
        return map;
    }
}