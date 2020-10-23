package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.service.EditMachineTypesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EditMachineTypesController {
    private final EditMachineTypesService service;

    public EditMachineTypesController(EditMachineTypesService service) {
        this.service = service;
    }

    @GetMapping("/editMachineTypes")
    public String goEditControlSystems(Model model) {
        List<MachineType> machineTypes = service.findAllMachineTypes();
        model.addAttribute("machineTypes", machineTypes);
        return "edit_machine_types";
    }

    @PostMapping("/machineTypeSave")
    @ResponseBody
    public MachineType saveMachineType(@RequestBody MachineType machineType) {
        return service.saveMachineType(machineType);
    }

    @PostMapping("/machineTypeUpdate")
    @ResponseBody
    public Boolean updateMachineType(@RequestBody MachineType machineType) {
        return service.updateMachineType(machineType);
    }

    @PostMapping("/machineTypeDelete")
    @ResponseBody
    public Boolean deleteControlSystem(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        return service.deleteMachineTypeById(id);
    }
}
