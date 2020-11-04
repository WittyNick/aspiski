package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Developer;
import by.gomselmash.aspiski.model.MachineType;
import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.model.dto.DateRageDto;
import by.gomselmash.aspiski.repository.DeveloperRepository;
import by.gomselmash.aspiski.repository.MachineTypeRepository;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatService {
    private final ProgramRepository programRepository;
    private final DeveloperRepository developerRepository;
    private final MachineTypeRepository machineTypeRepository;

    public StatService(
            ProgramRepository programRepository,
            DeveloperRepository developerRepository,
            MachineTypeRepository machineTypeRepository
    ) {
        this.programRepository = programRepository;
        this.developerRepository = developerRepository;
        this.machineTypeRepository = machineTypeRepository;
    }

    @Transactional(readOnly = true)
    public List<Program> getProgramsBetweenDates(DateRageDto dateRageDto) {
        LocalDate from = dateRageDto.getFrom();
        LocalDate to = dateRageDto.getTo();
        return programRepository.findAllByDateIsBetweenOrderByDateAscDeveloperAscMachine_MachineType_NameAsc(from, to);
    }

    @Transactional(readOnly = true)
    public long countAllPrograms() {
        return programRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<MachineType> findAllMachineTypes() {
        return machineTypeRepository.findAllByOrderByNameAsc();
    }

    public DateRageDto getDateRageNow() {
        LocalDate to = LocalDate.now();
        LocalDate from = LocalDate.of(to.getYear(), to.getMonth(), 1);
        return new DateRageDto(from, to);
    }
}
