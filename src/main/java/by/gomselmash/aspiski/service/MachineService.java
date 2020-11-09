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
public class MachineService {
    private final MachineRepository machineRepository;
    private final MachineTypeRepository machineTypeRepository;
    private final ProgramRepository programRepository;
    private final LogInService logInService;

    public MachineService(MachineRepository machineRepository,
                          MachineTypeRepository machineTypeRepository,
                          ProgramRepository programRepository,
                          LogInService logInService) {
        this.machineRepository = machineRepository;
        this.machineTypeRepository = machineTypeRepository;
        this.programRepository = programRepository;
        this.logInService = logInService;
    }

    @Transactional(readOnly = true)
    public List<Machine> findAllMachines() {
        return machineRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<MachineType> findAllMachineTypesSorted() {
        return machineTypeRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public Machine saveMachine(Machine machine) {
        String name = machine.getName();
        if (machineRepository.existsByNameIgnoreCase(name)) {
            return machine;
        }
        return machineRepository.save(machine);
    }

    @Transactional
    public boolean updateMachine(Machine machine) {
        Long id = machine.getId();
        if (machineRepository.existsById(id)) {
            machineRepository.save(machine);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteMachineById(Long id) {
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

    public String getAuthority() {
        String authority = "ROLE_ADMIN";
        if (logInService.isAuthorizationDisabled()) {
            authority = "DISABLED";
        }
        return authority;
    }
}
