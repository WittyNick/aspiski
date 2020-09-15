package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.repository.ControlSystemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AddControlSystemController {
    private final ControlSystemRepository controlSystemRepository;

    public AddControlSystemController(ControlSystemRepository controlSystemRepository) {
        this.controlSystemRepository = controlSystemRepository;
    }

    @GetMapping("/add-control-system")
    public String goAddControlSystem(Model model) {
        List<ControlSystem> controlSystems = controlSystemRepository.findAll(); // when nothing was found returns empty List
        model.addAttribute("controlSystems", controlSystems);
        return "add_control_system";
    }
}
