package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Developer;
import by.gomselmash.aspiski.service.EditDevelopersService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditDevelopersController {
    private final EditDevelopersService service;

    public EditDevelopersController(EditDevelopersService service) {
        this.service = service;
    }

    @GetMapping("/editDevelopers")
    public String goEditDevelopers(Model model) {
        List<Developer> developers = service.findAllDevelopersSorted();
        model.addAttribute("developers", developers);
        return "edit_developers";
    }

    @PostMapping("/developerSave")
    @ResponseBody
    public Developer saveDeveloper(@RequestBody Developer developer) {
        return service.saveDeveloper(developer);
    }

    @PostMapping("/developerDelete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeveloper(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        service.deleteDeveloperById(id);
    }
}
