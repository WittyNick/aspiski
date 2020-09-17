package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.service.EditProgramsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddProgramController {
    private final EditProgramsService editProgramsService;

    public AddProgramController(EditProgramsService editProgramsService) {
        this.editProgramsService = editProgramsService;
    }

    @GetMapping("/addProgram")
    public String goAddProgram() {
        return "add_program";
    }

    @PostMapping("/programSave")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveProgram(@RequestBody Program program) {
        editProgramsService.saveProgram(program);
    }
}
