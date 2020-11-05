package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.service.EditWorkshopsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EditWorkshopsController {
    private final EditWorkshopsService service;

    public EditWorkshopsController(EditWorkshopsService service) {
        this.service = service;
    }

    @GetMapping("/editWorkshops")
    public String goEditWorkshops(Model model) {
        List<Workshop> workshops = service.findAllWorkshops();
        model.addAttribute("entities", workshops);
        return "edit_workshops";
    }

    @PostMapping("/workshopSave")
    @ResponseBody
    public Workshop saveWorkshop(@RequestBody Workshop workshop) {
        return service.saveWorkshop(workshop);
    }

    @PostMapping("/workshopUpdate")
    @ResponseBody
    public Boolean updateWorkshop(@RequestBody Workshop workshop) {
        return service.updateWorkshop(workshop);
    }

    @PostMapping("/workshopDelete")
    @ResponseBody
    public Boolean deleteDeveloper(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteWorkshopById(id);
    }
}
