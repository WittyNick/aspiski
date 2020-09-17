package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.service.FindByPartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FindByPartController {
    private final FindByPartService findByPartService;

    public FindByPartController(FindByPartService findByPartService) {
        this.findByPartService = findByPartService;
    }

    @GetMapping("/findByPart")
    public String goFindByPart(Model model) {
        List<Program> programs = findByPartService.findAllPrograms();
        model.addAttribute("programs", programs);
        return "find_by_part";
    }
}
