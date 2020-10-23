package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.repository.ControlSystemRepository;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EditControlSystemsService {
    private final ControlSystemRepository controlSystemRepository;
    private final ProgramRepository programRepository;

    public EditControlSystemsService(
            ControlSystemRepository controlSystemRepository,
            ProgramRepository programRepository
    ) {
        this.controlSystemRepository = controlSystemRepository;
        this.programRepository = programRepository;
    }

    @Transactional(readOnly = true)
    public List<ControlSystem> findAllControlSystems() {
        return controlSystemRepository.findAllByOrderByNameAsc(); // when nothing was found returns empty List
    }

    public ControlSystem saveControlSystem(ControlSystem controlSystem) {
        String name = controlSystem.getName();
        if (controlSystemRepository.existsByNameIgnoreCase(name)) {
            return controlSystem;
        }
        return controlSystemRepository.save(controlSystem);
    }

    public Boolean updateControlSystem(ControlSystem controlSystem) {
        int id = controlSystem.getId();
        if (controlSystemRepository.existsById(id)) {
            controlSystemRepository.save(controlSystem);
            return true;
        }
        return false;
    }

    public boolean deleteControlSystemById(int id) {
        boolean isExists = controlSystemRepository.existsById(id);
        if (!isExists) {
            return false;
        }
        boolean isRelatedKey = programRepository.existsByControlSystem_Id(id);
        if (isRelatedKey) {
            return false;
        }
        controlSystemRepository.deleteById(id);
        return true;
    }
}
