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
    private final EditDevelopersService editDevelopersService;

    public EditDevelopersController(EditDevelopersService editDevelopersService) {
        this.editDevelopersService = editDevelopersService;
    }

    @GetMapping("/editDevelopers")
    public String goEditDevelopers(Model model) {
        List<Developer> developers = editDevelopersService.findAllDevelopers();
        model.addAttribute("developers", developers);
        return "edit_developers";
    }

    @PostMapping("/developerSave")
    @ResponseBody
    public Developer saveDeveloper(@RequestBody Developer developer) {
        return editDevelopersService.saveDeveloper(developer);
    }

    @PostMapping("/developerDelete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeveloper(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        editDevelopersService.deleteDeveloperById(id);
    }
}
