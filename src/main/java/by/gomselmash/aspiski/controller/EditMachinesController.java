package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Machine;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.service.EditMachinesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditMachinesController {
    private final EditMachinesService editMachinesService;

    public EditMachinesController(EditMachinesService editMachinesService) {
        this.editMachinesService = editMachinesService;
    }

    @GetMapping("/editMachines")
    public String goEditControlSystems(Model model) {
        List<Machine> machines = editMachinesService.findAllMachines();
        List<MachineType> machineTypes = editMachinesService.findAllMachineTypes();
        model
                .addAttribute("machines", machines)
                .addAttribute("machineTypes", machineTypes);
        return "edit_machines";
    }

//    @PostMapping("/machinesSave")
//    @ResponseBody
//    public ControlSystem saveControlSystem(@RequestBody ControlSystem controlSystem) {
//        return addControlSystemService.saveControlSystem(controlSystem);
//    }
//
//    // TODO: return boolean false when get exception or check possibility to delete
//    @PostMapping("/machinesDelete")
//    @ResponseStatus(value = HttpStatus.OK)
//    public void deleteControlSystem(@RequestBody String stringId) {
//        int id = Integer.parseInt(stringId);
//        addControlSystemService.deleteControlSystemById(id);
//    }
}
