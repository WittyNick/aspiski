package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Machine;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.repository.MachineRepository;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EditMachinesService {
    private final MachineRepository machineRepository;
    private final MachineTypeRepository machineTypeRepository;
    private final ProgramRepository programRepository;

    public EditMachinesService(
            MachineRepository machineRepository,
            MachineTypeRepository machineTypeRepository,
            ProgramRepository programRepository
    ) {
        this.machineRepository = machineRepository;
        this.machineTypeRepository = machineTypeRepository;
        this.programRepository = programRepository;
    }

    @Transactional(readOnly = true)
    public List<Machine> findAllMachines() {
        return machineRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<MachineType> findAllMachineTypesSorted() {
        return machineTypeRepository.findAllByOrderByNameAsc();
    }

    public Machine saveMachine(Machine machine) {
        String name = machine.getName();
        if (machineRepository.existsByNameIgnoreCase(name)) {
            return machine;
        }
        return machineRepository.save(machine);
    }

    public boolean updateMachine(Machine machine) {
        int id = machine.getId();
        if (machineRepository.existsById(id)) {
            machineRepository.save(machine);
            return true;
        }
        return false;
    }

    public boolean deleteMachineById(int id) {
        boolean isExists = machineRepository.existsById(id);
        if (!isExists) {
            return false;
        }
        boolean isRelatedKey = programRepository.existsByMachine_Id(id);
        if (isRelatedKey) {
            return false;
        }
        machineRepository.deleteById(id);
        return true;
    }
}
