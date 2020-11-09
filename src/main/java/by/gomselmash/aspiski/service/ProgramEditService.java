package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProgramEditService {
    private final ProgramRepository programRepository;
    private final MachineRepository machineRepository;
    private final ControlSystemRepository controlSystemRepository;
    private final WorkshopRepository workshopRepository;
    private final DeveloperRepository developerRepository;
    private final ProgramBaseService programBaseService;

    public ProgramEditService(ProgramRepository programRepository,
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
    public boolean updateProgram(Program program) {
        Long id = program.getId();
        if (!programRepository.existsById(id)) {
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
    public Optional<Program> findProgramById(Long id) {
        return programRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getEntityMap() {
        List<Machine> machines = machineRepository.findAllByOrderByNameAsc();
        List<ControlSystem> controlSystems = controlSystemRepository.findAllByOrderByNameAsc();
        List<Workshop> workshops = workshopRepository.findAllByOrderByNameAsc();
        List<Developer> developers = developerRepository.findAllByOrderByNameAsc();
        return programBaseService.getEntityMap(machines, controlSystems, workshops, developers);
    }

    public String getAuthority() {
        return programBaseService.getAuthority();
    }
}
