package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.repository.MachineRepository;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MachineTypeService {
    private final MachineTypeRepository machineTypeRepository;
    private final MachineRepository machineRepository;
    private final LogInService logInService;

    public MachineTypeService(MachineTypeRepository machineTypeRepository, MachineRepository machineRepository, LogInService logInService) {
        this.machineTypeRepository = machineTypeRepository;
        this.machineRepository = machineRepository;
        this.logInService = logInService;
    }

    @Transactional(readOnly = true)
    public List<MachineType> findAllMachineTypes() {
        return machineTypeRepository.findAllByOrderByNameAsc();
    }

    public MachineType saveMachineType(MachineType machineType) {
        String name = machineType.getName();
        if (machineTypeRepository.existsByNameIgnoreCase(name)) {
            return machineType;
        }
        return machineTypeRepository.save(machineType);
    }

    @Transactional
    public Boolean updateMachineType(MachineType machineType) {
        Long id = machineType.getId();
        if (machineTypeRepository.existsById(id)) {
            machineTypeRepository.save(machineType);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteMachineTypeById(Long id) {
        boolean isExists = machineTypeRepository.existsById(id);
        if (!isExists) {
            return false;
        }
        boolean isRelatedKey = machineRepository.existsByMachineType_Id(id);
        if (isRelatedKey) {
            return false;
        }
        machineTypeRepository.deleteById(id);
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
