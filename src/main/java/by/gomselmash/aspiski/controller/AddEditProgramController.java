package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.AddEditProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AddEditProgramController {
    private final AddEditProgramService service;

    public AddEditProgramController(AddEditProgramService service) {
        this.service = service;
    }

    @GetMapping("/addProgram")
    public String goAddProgram(@CookieValue(value = "userId", defaultValue = "0") String userId, Model model) {
        Integer id = Integer.valueOf(userId);
        model
                .addAllAttributes(getAttributeMap())
                .addAttribute("userId", id);
        return "add_program";
    }

    @PostMapping("/editProgram")
    public String goEditProgram(@RequestParam String programId, Model model) {
        Long id = Long.valueOf(programId);
        Optional<Program> optionalProgram = service.findProgramById(id);
        if (optionalProgram.isPresent()) {
            model
                    .addAllAttributes(getAttributeMap())
                    .addAttribute("program", optionalProgram.get());
            return "edit_program";
        }
        return "redirect:/";
    }

    @PostMapping("/programSave")
    @ResponseBody
    public Boolean saveProgram(@RequestBody Program program, HttpServletResponse response) {
        String userId = String.valueOf(program.getDeveloper().getId());
        Cookie cookie = new Cookie("userId", userId);
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return service.saveProgram(program);
    }

    @PostMapping("/programUpdate")
    @ResponseBody
    public Boolean updateProgram(@RequestBody Program program) {
        return service.updateProgram(program);
    }

    private Map<String, Object> getAttributeMap() {
        List<Machine> machines = service.findAllMachines();
        List<ControlSystem> controlSystems = service.findAllControlSystems();
        List<Workshop> workshops = service.findAllWorkshops();
        List<Developer> developers = service.findAllDevelopers();
        Map<String, Object> map = new HashMap<>();
        map.put("machines", machines);
        map.put("controlSystems", controlSystems);
        map.put("workshops", workshops);
        map.put("developers", developers);
        return map;
    }
}