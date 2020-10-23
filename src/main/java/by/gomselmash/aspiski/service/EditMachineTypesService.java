package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.repository.MachineRepository;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EditMachineTypesService {
    private final MachineTypeRepository machineTypeRepository;
    private final MachineRepository machineRepository;

    public EditMachineTypesService(
            MachineTypeRepository machineTypeRepository,
            MachineRepository machineRepository
    ) {
        this.machineTypeRepository = machineTypeRepository;
        this.machineRepository = machineRepository;
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

    public Boolean updateMachineType(MachineType machineType) {
        int id = machineType.getId();
        if (machineTypeRepository.existsById(id)) {
            machineTypeRepository.save(machineType);
            return true;
        }
        return false;
    }

    public boolean deleteMachineTypeById(int id) {
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
}
