package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Machine;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.repository.MachineRepository;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditMachinesService {
    private final MachineRepository machineRepository;
    private final MachineTypeRepository machineTypeRepository;

    public EditMachinesService(MachineRepository machineRepository, MachineTypeRepository machineTypeRepository) {
        this.machineRepository = machineRepository;
        this.machineTypeRepository = machineTypeRepository;
    }

    public List<Machine> findAllMachinesSorted() {
        return machineRepository.findAllByOrderByNameAsc();
    }

    public List<MachineType> findAllMachineTypes() {
        return machineTypeRepository.findAll();
    }

    @Transactional
    public Machine saveMachine(Machine machine) {
        String name = machine.getName();
        if (machineRepository.existsByNameIgnoreCase(name)) {
            return machine;
        }
        return machineRepository.save(machine);
    }

    public void deleteMachineById(int id) {
        machineRepository.deleteById(id);
    }
}
