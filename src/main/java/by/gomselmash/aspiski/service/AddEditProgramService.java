package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddEditProgramService {
    private final ProgramRepository programRepository;
    private final MachineRepository machineRepository;
    private final ControlSystemRepository controlSystemRepository;
    private final WorkshopRepository workshopRepository;
    private final DeveloperRepository developerRepository;

    public AddEditProgramService(ProgramRepository programRepository,
                                 MachineRepository machineRepository,
                                 ControlSystemRepository controlSystemRepository,
                                 WorkshopRepository workshopRepository,
                                 DeveloperRepository developerRepository) {
        this.programRepository = programRepository;
        this.machineRepository = machineRepository;
        this.controlSystemRepository = controlSystemRepository;
        this.workshopRepository = workshopRepository;
        this.developerRepository = developerRepository;
    }

    @Transactional
    public boolean saveProgram(Program program) {

        String programNumber = program.getProgramNumber();
        if (programRepository.existsByProgramNumberIgnoreCase(programNumber)) {
            return false;
        }
        rearrangeProgramNumber(program);
        programRepository.save(program);
        return true;
    }

    @Transactional
    public boolean updateProgram(Program program) {
        Long id = program.getId();
        if (programRepository.existsById(id)) {
            rearrangeProgramNumber(program);
            programRepository.save(program);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<Machine> findAllMachines() {
        return machineRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<ControlSystem> findAllControlSystems() {
        return controlSystemRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<Workshop> findAllWorkshops() {
        return workshopRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public Optional<Program> findProgramById(Long id) {
        return programRepository.findById(id);
    }

    @Transactional(readOnly = true)
    boolean isEveryRelatedItemExists(Program program) {
        Long controlSystemId = program.getControlSystem().getId();
        Long developerId = program.getDeveloper().getId();
        Long machineId = program.getMachine().getId();
        Long workshopId = program.getWorkshop().getId();
        return controlSystemRepository.existsById(controlSystemId) &&
                developerRepository.existsById(developerId) &&
                machineRepository.existsById(machineId) &&
                workshopRepository.existsById(workshopId);
    }

    private void rearrangeProgramNumber(Program program) {
        String[] parts = program.getPartNumber().split(",");
        StringBuilder partNumber = new StringBuilder(parts[0].trim());
        for (int i = 1; i < parts.length; i++) {
            partNumber.append(",\n").append(parts[i].trim());
        }
        program.setPartNumber(partNumber.toString());
    }
}
