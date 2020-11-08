package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.service.ControlSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ControlSystemsEditController {
    private final ControlSystemService service;

    public ControlSystemsEditController(ControlSystemService service) {
        this.service = service;
    }

    @GetMapping("/controlSystemsEdit")
    public String goControlSystemsEdit(Model model) {
        List<ControlSystem> controlSystems = service.findAllControlSystems();
        model.addAttribute("entities", controlSystems);
        return "control_systems_edit";
    }

    @PostMapping("/saveControlSystem")
    @ResponseBody
    public ControlSystem saveControlSystem(@RequestBody ControlSystem controlSystem) {
        return service.saveControlSystem(controlSystem);
    }

    @PostMapping("/updateControlSystem")
    @ResponseBody
    public Boolean updateControlSystem(@RequestBody ControlSystem controlSystem) {
        return service.updateControlSystem(controlSystem);
    }

    @PostMapping("/deleteControlSystem")
    @ResponseBody
    public Boolean deleteControlSystem(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteControlSystemById(id);
    }
}
