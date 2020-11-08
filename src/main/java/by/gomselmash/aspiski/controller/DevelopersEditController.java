package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Developer;
import by.gomselmash.aspiski.service.DeveloperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DevelopersEditController {
    private final DeveloperService service;

    public DevelopersEditController(DeveloperService service) {
        this.service = service;
    }

    @GetMapping("/developersEdit")
    public String goDevelopersEdit(Model model) {
        List<Developer> developers = service.findAllDevelopers();
        model.addAttribute("entities", developers);
        return "developers_edit";
    }

    @PostMapping("/saveDeveloper")
    @ResponseBody
    public Developer saveDeveloper(@RequestBody Developer developer) {
        return service.saveDeveloper(developer);
    }

    @PostMapping("/updateDeveloper")
    @ResponseBody
    public Boolean updateDeveloper(@RequestBody Developer developer) {
        return service.updateDeveloper(developer);
    }

    @PostMapping("/deleteDeveloper")
    @ResponseBody
    public Boolean deleteDeveloper(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteDeveloperById(id);
    }
}
