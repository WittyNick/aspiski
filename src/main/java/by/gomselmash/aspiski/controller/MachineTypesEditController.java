package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.service.MachineTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MachineTypesEditController {
    private final MachineTypeService service;

    public MachineTypesEditController(MachineTypeService service) {
        this.service = service;
    }

    @GetMapping("/machineTypesEdit")
    public String goMachineTypesEdit(Model model) {
        List<MachineType> machineTypes = service.findAllMachineTypes();
        String authority = service.getAuthority();
        model
                .addAttribute("entities", machineTypes)
                .addAttribute("authority", authority);
        return "machine_types_edit";
    }

    @PostMapping("/saveMachineType")
    @ResponseBody
    public MachineType saveMachineType(@RequestBody MachineType machineType) {
        return service.saveMachineType(machineType);
    }

    @PostMapping("/updateMachineType")
    @ResponseBody
    public Boolean updateMachineType(@RequestBody MachineType machineType) {
        return service.updateMachineType(machineType);
    }

    @PostMapping("/deleteMachineType")
    @ResponseBody
    public Boolean deleteMachineType(@RequestBody String stringId) {
        Long id = Long.valueOf(stringId);
        return service.deleteMachineTypeById(id);
    }
}
