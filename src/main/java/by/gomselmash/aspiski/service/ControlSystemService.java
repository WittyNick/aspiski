package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.repository.ControlSystemRepository;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ControlSystemService {
    private final ControlSystemRepository controlSystemRepository;
    private final ProgramRepository programRepository;
    private final LogInService logInService;

    public ControlSystemService(ControlSystemRepository controlSystemRepository,
                                ProgramRepository programRepository,
                                LogInService logInService) {
        this.controlSystemRepository = controlSystemRepository;
        this.programRepository = programRepository;
        this.logInService = logInService;
    }

    @Transactional(readOnly = true)
    public List<ControlSystem> findAllControlSystems() {
        return controlSystemRepository.findAllByOrderByNameAsc(); // when nothing was found returns empty List
    }

    @Transactional
    public ControlSystem saveControlSystem(ControlSystem controlSystem) {
        String name = controlSystem.getName();
        if (controlSystemRepository.existsByNameIgnoreCase(name)) {
            return controlSystem;
        }
        return controlSystemRepository.save(controlSystem);
    }

    @Transactional
    public Boolean updateControlSystem(ControlSystem controlSystem) {
        Long id = controlSystem.getId();
        if (controlSystemRepository.existsById(id)) {
            controlSystemRepository.save(controlSystem);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteControlSystemById(Long id) {
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

    public String getAuthority() {
        String authority = "ROLE_ADMIN";
        if (logInService.isAuthorizationDisabled()) {
            authority = "DISABLED";
        }
        return authority;
    }
}
