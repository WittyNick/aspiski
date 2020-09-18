package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.service.AddControlSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditControlSystemsController {
    private final AddControlSystemService addControlSystemService;

    public EditControlSystemsController(AddControlSystemService addControlSystemService) {
        this.addControlSystemService = addControlSystemService;
    }

    @GetMapping("/editControlSystems")
    public String goEditControlSystems(Model model) {
        List<ControlSystem> controlSystems = addControlSystemService.findAllControlSystems();
        model.addAttribute("controlSystems", controlSystems);
        return "edit_control_systems";
    }

    @PostMapping("/controlSystemSave")
    @ResponseBody
    public ControlSystem saveControlSystem(@RequestBody ControlSystem controlSystem) {
        return addControlSystemService.saveControlSystem(controlSystem);
    }

    // TODO: return boolean false when get exception or check possibility to delete
    @PostMapping("/controlSystemDelete")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteControlSystem(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        addControlSystemService.deleteControlSystemById(id);
    }
}
