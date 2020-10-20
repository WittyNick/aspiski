package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditMachineTypesService {
    private final MachineTypeRepository machineTypeRepository;

    public EditMachineTypesService(MachineTypeRepository machineTypeRepository) {
        this.machineTypeRepository = machineTypeRepository;
    }

    public List<MachineType> findAllMachineTypesSorted() {
        return machineTypeRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public MachineType saveMachineType(MachineType machineType) {
        String name = machineType.getName();
        if (machineTypeRepository.existsByNameIgnoreCase(name)) {
            return machineType;
        }
        return machineTypeRepository.save(machineType);
    }

    public void deleteMachineTypeById(int id) {
        machineTypeRepository.deleteById(id);
    }
}
