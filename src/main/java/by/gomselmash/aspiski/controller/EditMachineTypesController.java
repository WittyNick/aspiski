package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.service.EditMachineTypesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EditMachineTypesController {
    private final EditMachineTypesService editMachineTypesService;

    public EditMachineTypesController(EditMachineTypesService editMachineTypesService) {
        this.editMachineTypesService = editMachineTypesService;
    }

    @GetMapping("/editMachineTypes")
    public String goEditControlSystems(Model model) {
        List<MachineType> machineTypes = editMachineTypesService.findAllMachineTypes();
        model.addAttribute("machineTypes", machineTypes);
        return "edit_machine_types";
    }

    @PostMapping("/machineTypeSave")
    @ResponseBody
    public MachineType saveMachineType(@RequestBody MachineType machineType) {
        return editMachineTypesService.saveMachineType(machineType);
    }

    // TODO: return boolean false when get exception or check possibility to delete
    @PostMapping("/machineTypeDelete")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteControlSystem(@RequestBody String stringId) {
        int id = Integer.parseInt(stringId);
        editMachineTypesService.deleteMachineTypeById(id);
    }
}
