package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Machine;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.repository.MachineRepository;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditMachinesService {
    private final MachineRepository machineRepository;
    private final MachineTypeRepository machineTypeRepository;

    public EditMachinesService(MachineRepository machineRepository, MachineTypeRepository machineTypeRepository) {
        this.machineRepository = machineRepository;
        this.machineTypeRepository = machineTypeRepository;
    }

    public List<Machine> findAllMachines() {
        return machineRepository.findAll();
    }

    public List<MachineType> findAllMachineTypes() {
        return machineTypeRepository.findAll();
    }

    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public void deleteMachineById(int id) {
        machineRepository.deleteById(id);
    }
}
