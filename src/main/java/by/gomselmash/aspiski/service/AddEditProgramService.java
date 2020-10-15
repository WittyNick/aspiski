package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void saveProgram(Program programToSave) {
        programRepository.save(programToSave);
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

    public Optional<Program> findProgramById(int id) {
        return programRepository.findById(id);
    }

    public Optional<Program> findProgramById(String id) {
        return findProgramById(Integer.parseInt(id));
    }

    /*
     LocalDate currentDate = LocalDate.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     */
    public String currentDate() {
        return LocalDate.now().toString(); // yyyy-MM-dd
    }
}
