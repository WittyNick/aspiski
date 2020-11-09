package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.ControlSystemRepository;
import by.gomselmash.aspiski.repository.DeveloperRepository;
import by.gomselmash.aspiski.repository.MachineRepository;
import by.gomselmash.aspiski.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgramBaseService {
    private final MachineRepository machineRepository;
    private final ControlSystemRepository controlSystemRepository;
    private final WorkshopRepository workshopRepository;
    private final DeveloperRepository developerRepository;
    private final LogInService logInService;

    public ProgramBaseService(MachineRepository machineRepository,
                              ControlSystemRepository controlSystemRepository,
                              WorkshopRepository workshopRepository,
                              DeveloperRepository developerRepository,
                              LogInService logInService) {
        this.machineRepository = machineRepository;
        this.controlSystemRepository = controlSystemRepository;
        this.workshopRepository = workshopRepository;
        this.developerRepository = developerRepository;
        this.logInService = logInService;
    }

    @Transactional(readOnly = true)
    boolean hasInvalidFields(Program program) {
        Long controlSystemId = program.getControlSystem().getId();
        Long developerId = program.getDeveloper().getId();
        Long machineId = program.getMachine().getId();
        Long workshopId = program.getWorkshop().getId();
        return !controlSystemRepository.existsById(controlSystemId) ||
                !developerRepository.existsById(developerId) ||
                !machineRepository.existsById(machineId) ||
                !workshopRepository.existsById(workshopId);
    }

    public void rearrangeProgramNumber(Program program) {
        String[] parts = program.getPartNumber().split(",");
        StringBuilder partNumber = new StringBuilder(parts[0].trim());
        for (int i = 1; i < parts.length; i++) {
            partNumber.append(",\n").append(parts[i].trim());
        }
        program.setPartNumber(partNumber.toString());
    }

    public String getAuthority() {
        String authority = "ROLE_ADMIN";
        if (logInService.isAuthorizationDisabled()) {
            authority = "DISABLED";
        }
        return authority;
    }

    public Map<String, Object> getEntityMap(List<Machine> machines,
                                            List<ControlSystem> controlSystems,
                                            List<Workshop> workshops,
                                            List<Developer> developers) {
        Map<String, Object> map = new HashMap<>();
        map.put("machines", machines);
        map.put("controlSystems", controlSystems);
        map.put("workshops", workshops);
        map.put("developers", developers);
        return map;
    }
}