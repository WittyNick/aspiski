package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Developer;
import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.service.EditWorkshopsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditWorkshopsController {
    private final EditWorkshopsService editWorkshopsService;

    public EditWorkshopsController(EditWorkshopsService editWorkshopsService) {
        this.editWorkshopsService = editWorkshopsService;
    }

    @GetMapping("/editWorkshops")
    public String goEditWorkshops(Model model) {
        List<Workshop> workshops = editWorkshopsService.findAllWorkshopsSorted();
        model.addAttribute("workshops", workshops);
        return "edit_workshops";
    }

    @PostMapping("/workshopSave")
    @ResponseBody
    public Workshop saveWorkshop(@RequestBody Workshop workshop) {
        return editWorkshopsService.saveWorkshop(workshop);
    }

    @PostMapping("/workshopDelete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeveloper(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        editWorkshopsService.deleteWorkshopById(id);
    }
}
