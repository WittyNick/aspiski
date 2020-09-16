package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.service.AddProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddProgramController {
    private final AddProgramService addProgramService;

    public AddProgramController(AddProgramService addProgramService) {
        this.addProgramService = addProgramService;
    }

    @GetMapping("/add-program")
    public String goAddProgram(Model model) {
        List<Program> programs = addProgramService.findAllPrograms();
        model.addAttribute("programs", programs);
        return "add_program";
    }

    @PostMapping("/programSave")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveProgram(@RequestBody Program program) {
        addProgramService.saveProgram(program);
    }
}
