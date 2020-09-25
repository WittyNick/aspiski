package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditMachineTypesService {
    private final MachineTypeRepository machineTypeRepository;

    public EditMachineTypesService(MachineTypeRepository machineTypeRepository) {
        this.machineTypeRepository = machineTypeRepository;
    }

    public List<MachineType> findAllMachineTypes() {
        return machineTypeRepository.findAll();
    }

    public MachineType saveMachineType(MachineType machineType) {
        return machineTypeRepository.save(machineType);
    }

    public void deleteMachineTypeById(int id) {
        machineTypeRepository.deleteById(id);
    }
}
