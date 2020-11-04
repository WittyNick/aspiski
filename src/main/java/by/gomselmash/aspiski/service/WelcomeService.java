package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WelcomeService {
    private final ProgramRepository programRepository;
    private final MachineRepository machineRepository;
    private final ControlSystemRepository controlSystemRepository;
    private final WorkshopRepository workshopRepository;
    private final DeveloperRepository developerRepository;

    public WelcomeService(ProgramRepository programRepository,
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

    @Transactional(readOnly = true)
    public List<Program> findAllPrograms() {
        return programRepository.findAllByOrderByPartNumberAsc();
    }

    @Transactional
    public boolean deleteProgramById(Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
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
}
