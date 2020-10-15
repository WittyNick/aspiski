package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;

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

    public List<Program> findAllProgramsSorted() {
        return programRepository.findAllByOrderByPartNumberAsc();
    }

    public void deleteProgramById(int id) {
        programRepository.deleteById(id);
    }

    public List<Machine> findAllMachinesSorted() {
        return machineRepository.findAllByOrderByNameAsc();
    }

    public List<ControlSystem> findAllControlSystemsSorted() {
        return controlSystemRepository.findAllByOrderByNameAsc();
    }

    public List<Workshop> findAllWorkshopsSorted() {
        return workshopRepository.findAllByOrderByNameAsc();
    }

    public List<Developer> findAllDevelopersSorted() {
        return developerRepository.findAllByOrderByNameAsc();
    }
}
