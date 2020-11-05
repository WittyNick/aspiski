package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Developer;
import by.gomselmash.aspiski.service.EditDevelopersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EditDevelopersController {
    private final EditDevelopersService service;

    public EditDevelopersController(EditDevelopersService service) {
        this.service = service;
    }

    @GetMapping("/editDevelopers")
    public String goEditDevelopers(Model model) {
        List<Developer> developers = service.findAllDevelopers();
        model.addAttribute("entities", developers);
        return "edit_developers";
    }

    @PostMapping("/developerSave")
    @ResponseBody
    public Developer saveDeveloper(@RequestBody Developer developer) {
        return service.saveDeveloper(developer);
    }

    @PostMapping("/developerUpdate")
    @ResponseBody
    public Boolean updateDeveloper(@RequestBody Developer developer) {
        return service.updateDeveloper(developer);
    }

    @PostMapping("/developerDelete")
    @ResponseBody
    public Boolean deleteDeveloper(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteDeveloperById(id);
    }
}
