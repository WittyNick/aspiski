package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.service.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

@Controller
public class ProgramEditController {
    private final ProgramService service;

    public ProgramEditController(ProgramService service) {
        this.service = service;
    }

    @PostMapping("/programEdit")
    public String goProgramEdit(@RequestParam String programId, Model model) {
        Long id = Long.valueOf(programId);
        Optional<Program> optionalProgram = service.findProgramById(id);
        if (optionalProgram.isPresent()) {
            Map<String, Object> dropdownMap = service.getEntityMap();
            Program program = optionalProgram.get();
            String authority = service.getAuthority();
            model
                    .addAllAttributes(dropdownMap)
                    .addAttribute("program", program)
                    .addAttribute("authority", authority);
            return "program_edit";
        }
        return "redirect:/";
    }

    @PostMapping("/updateProgram")
    @ResponseBody
    public Boolean programUpdate(@RequestBody Program program) {
        return service.updateProgram(program);
    }
}