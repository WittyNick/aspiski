package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Machine;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.service.EditMachinesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EditMachinesController {
    private final EditMachinesService service;

    public EditMachinesController(EditMachinesService service) {
        this.service = service;
    }

    @GetMapping("/editMachines")
    public String goEditMachines(Model model) {
        List<Machine> machines = service.findAllMachinesSorted();
        List<MachineType> machineTypes = service.findAllMachineTypesSorted();
        model
                .addAttribute("machines", machines)
                .addAttribute("machineTypes", machineTypes);
        return "edit_machines";
    }

    @PostMapping("/machineSave")
    @ResponseBody
    public Machine saveMachine(@RequestBody Machine machine) {
        return service.saveMachine(machine);
    }

    @PostMapping("/machineUpdate")
    @ResponseBody
    public Boolean updateMachine(@RequestBody Machine machine) {
        return service.updateMachine(machine);
    }

    @PostMapping("/machineDelete")
    @ResponseBody
    public Boolean deleteMachine(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        return service.deleteMachineById(id);
    }
}
