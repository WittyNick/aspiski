package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WorkshopsEditController {
    private final WorkshopService service;

    public WorkshopsEditController(WorkshopService service) {
        this.service = service;
    }

    @GetMapping("/workshopsEdit")
    public String goWorkshopsEdit(Model model) {
        List<Workshop> workshops = service.findAllWorkshops();
        String authority = service.getAuthority();
        model
                .addAttribute("entities", workshops)
                .addAttribute("authority", authority);
        return "workshops_edit";
    }

    @PostMapping("/saveWorkshop")
    @ResponseBody
    public Workshop saveWorkshop(@RequestBody Workshop workshop) {
        return service.saveWorkshop(workshop);
    }

    @PostMapping("/updateWorkshop")
    @ResponseBody
    public Boolean updateWorkshop(@RequestBody Workshop workshop) {
        return service.updateWorkshop(workshop);
    }

    @PostMapping("/deleteWorkshop")
    @ResponseBody
    public Boolean deleteWorkshop(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteWorkshopById(id);
    }
}
