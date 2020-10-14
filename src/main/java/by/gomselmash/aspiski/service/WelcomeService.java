package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Program> findAllPrograms() {
        return programRepository.findAll();
    }

    public void deleteProgramById(int id) {
        programRepository.deleteById(id);
    }

    public List<Machine> findAllMachines() {
        return machineRepository.findAll();
    }

    public List<ControlSystem> findAllControlSystems() {
        return controlSystemRepository.findAll();
    }

    public List<Workshop> findAllWorkshops() {
        return workshopRepository.findAll();
    }

    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }
}
