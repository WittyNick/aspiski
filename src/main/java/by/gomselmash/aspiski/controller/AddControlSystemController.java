package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.repository.ControlSystemRepository;
import by.gomselmash.aspiski.service.AddControlSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddControlSystemController {
    private final AddControlSystemService addControlSystemService;

    public AddControlSystemController(AddControlSystemService addControlSystemService) {
        this.addControlSystemService = addControlSystemService;
    }

    @GetMapping("/add-control-system")
    public String goAddControlSystem(Model model) {
        List<ControlSystem> controlSystems = addControlSystemService.findAllControlSystems();
        model.addAttribute("controlSystems", controlSystems);
        return "add_control_system";
    }

    @PostMapping("/controlSystemSave")
    @ResponseBody
    public ControlSystem saveControlSystem(@RequestBody ControlSystem controlSystem) {
        return addControlSystemService.saveControlSystem(controlSystem);
    }
}
