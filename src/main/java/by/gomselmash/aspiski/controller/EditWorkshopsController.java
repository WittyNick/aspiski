package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.service.EditWorkshopsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditWorkshopsController {
    private final EditWorkshopsService service;

    public EditWorkshopsController(EditWorkshopsService service) {
        this.service = service;
    }

    @GetMapping("/editWorkshops")
    public String goEditWorkshops(Model model) {
        List<Workshop> workshops = service.findAllWorkshopsSorted();
        model.addAttribute("workshops", workshops);
        return "edit_workshops";
    }

    @PostMapping("/workshopSave")
    @ResponseBody
    public Workshop saveWorkshop(@RequestBody Workshop workshop) {
        return service.saveWorkshop(workshop);
    }

    @PostMapping("/workshopDelete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeveloper(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        service.deleteWorkshopById(id);
    }
}
