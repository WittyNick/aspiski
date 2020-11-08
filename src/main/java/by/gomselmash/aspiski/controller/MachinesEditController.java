package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Machine;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MachinesEditController {
    private final MachineService service;

    public MachinesEditController(MachineService service) {
        this.service = service;
    }

    @GetMapping("/machinesEdit")
    public String goMachinesEdit(Model model) {
        List<Machine> machines = service.findAllMachines();
        List<MachineType> machineTypes = service.findAllMachineTypesSorted();
        model
                .addAttribute("machines", machines)
                .addAttribute("machineTypes", machineTypes);
        return "machines_edit";
    }

    @PostMapping("/saveMachine")
    @ResponseBody
    public Machine saveMachine(@RequestBody Machine machine) {
        return service.saveMachine(machine);
    }

    @PostMapping("/updateMachine")
    @ResponseBody
    public Boolean updateMachine(@RequestBody Machine machine) {
        return service.updateMachine(machine);
    }

    @PostMapping("/deleteMachine")
    @ResponseBody
    public Boolean deleteMachine(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteMachineById(id);
    }
}
