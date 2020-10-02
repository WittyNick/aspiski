package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.AddEditProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AddEditProgramController {
    private final AddEditProgramService service;

    public AddEditProgramController(AddEditProgramService service) {
        this.service = service;
    }

    @GetMapping("/addProgram")
    public ModelAndView goAddProgram() {
        String currentDate = service.currentDate();
        ModelAndView modelAndView = getModelAndViewWithAttributes("add_program");
        modelAndView.addObject("currentDate", currentDate);
        return modelAndView;
    }

    @PostMapping("/editProgram")
    public ModelAndView goEditProgram(@RequestParam String programId) {
        int id = Integer.parseInt(programId);
        Program program = service.findProgramById(id); // when program wasn't found returns new Program()
        if (program.getId() == 0) {
            return new ModelAndView("redirect:/findByPart");
        }
        ModelAndView modelAndView = getModelAndViewWithAttributes("edit_program");
        modelAndView.addObject("program", program);
        return modelAndView;
    }

    @PostMapping("/programSave")
    @ResponseStatus(HttpStatus.OK)
    public void saveProgram(@RequestBody Program program) {
        service.saveProgram(program);
    }

    private ModelAndView getModelAndViewWithAttributes(String viewName) {
        List<Machine> machines = service.findAllMachines();
        List<ControlSystem> controlSystems = service.findAllControlSystems();
        List<Workshop> workshops = service.findAllWorkshops();
        List<Developer> developers = service.findAllDevelopers();
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView
                .addObject("machines", machines)
                .addObject("controlSystems", controlSystems)
                .addObject("workshops", workshops)
                .addObject("developers", developers);
        return modelAndView;
    }
}