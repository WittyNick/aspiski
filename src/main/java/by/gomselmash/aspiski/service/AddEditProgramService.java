package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        programRepository.save(program);
        return true;
    }

    @Transactional
    public boolean updateProgram(Program program) {
        Integer id = program.getId();
        if (programRepository.existsById(id)) {
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
    public Optional<Program> findProgramById(int id) {
        return programRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Program> findProgramById(String id) {
        return findProgramById(Integer.parseInt(id));
    }

    /*
     LocalDate currentDate = LocalDate.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     */
    public String getDateNow() {
        return LocalDate.now().toString(); // yyyy-MM-dd
    }
}
