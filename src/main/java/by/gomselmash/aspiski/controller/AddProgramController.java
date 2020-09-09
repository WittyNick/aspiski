package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.service.AddProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/add-program")
public class AddProgramController {
    private final AddProgramService addProgramService;

    public AddProgramController(AddProgramService addProgramService) {
        this.addProgramService = addProgramService;
    }

    @GetMapping
    public String getProgram() {
        return "add_program";
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveProgram(@RequestBody Program program) {
        addProgramService.saveProgram(program);
        return "ok";
    }
}
