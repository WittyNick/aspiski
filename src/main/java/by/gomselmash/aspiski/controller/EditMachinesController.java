package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Machine;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.service.EditMachinesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditMachinesController {
    private final EditMachinesService service;

    public EditMachinesController(EditMachinesService service) {
        this.service = service;
    }

    @GetMapping("/editMachines")
    public String goEditControlSystems(Model model) {
        List<Machine> machines = service.findAllMachinesSorted();
        List<MachineType> machineTypes = service.findAllMachineTypes();
        model
                .addAttribute("machines", machines)
                .addAttribute("machineTypes", machineTypes);
        return "edit_machines";
    }

    @PostMapping("/machineSave")
    @ResponseBody
    public Machine saveControlSystem(@RequestBody Machine machine) {
        return service.saveMachine(machine);
    }

    // TODO: return boolean false when get exception or check possibility to delete
    @PostMapping("/machineDelete")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteControlSystem(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        service.deleteMachineById(id);
    }
}
