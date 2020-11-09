package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProgramAddService {
    private final ProgramRepository programRepository;
    private final MachineRepository machineRepository;
    private final ControlSystemRepository controlSystemRepository;
    private final WorkshopRepository workshopRepository;
    private final DeveloperRepository developerRepository;
    private final ProgramBaseService programBaseService;

    public ProgramAddService(ProgramRepository programRepository,
                             MachineRepository machineRepository,
                             ControlSystemRepository controlSystemRepository,
                             WorkshopRepository workshopRepository,
                             DeveloperRepository developerRepository,
                             ProgramBaseService programBaseService) {
        this.programRepository = programRepository;
        this.machineRepository = machineRepository;
        this.controlSystemRepository = controlSystemRepository;
        this.workshopRepository = workshopRepository;
        this.developerRepository = developerRepository;
        this.programBaseService = programBaseService;
    }

    @Transactional
    public boolean saveProgram(Program program) {
        String programNumber = program.getProgramNumber();
        if (programRepository.existsByProgramNumberIgnoreCase(programNumber)) {
            return false;
        }
        if (programBaseService.hasInvalidFields(program)) {
            return false;
        }
        programBaseService.rearrangeProgramNumber(program);
        programRepository.save(program);
        return true;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getEntityMap() {
        List<Machine> machines = machineRepository.findAllByIsDisabledFalseAndMachineType_IsDisabledFalseOrderByNameAsc();
        List<ControlSystem> controlSystems = controlSystemRepository.findAllByIsDisabledFalseOrderByNameAsc();
        List<Workshop> workshops = workshopRepository.findAllByIsDisabledFalseOrderByNameAsc();
        List<Developer> developers = developerRepository.findAllByIsDisabledFalseOrderByNameAsc();
        return programBaseService.getEntityMap(machines, controlSystems, workshops, developers);
    }

    public String getAuthority() {
        return programBaseService.getAuthority();
    }
}
