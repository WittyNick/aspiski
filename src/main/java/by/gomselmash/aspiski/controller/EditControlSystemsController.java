package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.service.EditControlSystemsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditControlSystemsController {
    private final EditControlSystemsService service;

    public EditControlSystemsController(EditControlSystemsService service) {
        this.service = service;
    }

    @GetMapping("/editControlSystems")
    public String goEditControlSystems(Model model) {
        List<ControlSystem> controlSystems = service.findAllControlSystemsSorted();
        model.addAttribute("controlSystems", controlSystems);
        return "edit_control_systems";
    }

    @PostMapping("/controlSystemSave")
    @ResponseBody
    public ControlSystem saveControlSystem(@RequestBody ControlSystem controlSystem) {
        return service.saveControlSystem(controlSystem);
    }

    // TODO: return boolean false when get exception or check possibility to delete
    @PostMapping("/controlSystemDelete")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteControlSystem(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        service.deleteControlSystemById(id);
    }
}
