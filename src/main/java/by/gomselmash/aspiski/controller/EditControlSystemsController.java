package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.service.EditControlSystemsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EditControlSystemsController {
    private final EditControlSystemsService service;

    public EditControlSystemsController(EditControlSystemsService service) {
        this.service = service;
    }

    @GetMapping("/editControlSystems")
    public String goEditControlSystems(Model model) {
        List<ControlSystem> controlSystems = service.findAllControlSystems();
        model.addAttribute("entities", controlSystems);
        return "edit_control_systems";
    }

    @PostMapping("/controlSystemSave")
    @ResponseBody
    public ControlSystem saveControlSystem(@RequestBody ControlSystem controlSystem) {
        return service.saveControlSystem(controlSystem);
    }

    @PostMapping("/controlSystemUpdate")
    @ResponseBody
    public Boolean updateControlSystem(@RequestBody ControlSystem controlSystem) {
        return service.updateControlSystem(controlSystem);
    }

    @PostMapping("/controlSystemDelete")
    @ResponseBody
    public Boolean deleteControlSystem(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteControlSystemById(id);
    }
}
