package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.model.dto.DateRageDto;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatService {
    private final ProgramRepository programRepository;

    public StatService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Transactional(readOnly = true)
    public List<Program> getProgramsBetweenDates(DateRageDto dateRageDto) {
        LocalDate from = dateRageDto.getFrom();
        LocalDate to = dateRageDto.getTo();
        return programRepository.findAllByDateIsBetweenOrderByDateAscDeveloperAscMachine_MachineType_NameAsc(from, to);
    }

    public DateRageDto getDateRageNow() {
        LocalDate to = LocalDate.now();
        LocalDate from = LocalDate.of(to.getYear(), to.getMonth(), 1);
        return new DateRageDto(from, to);
    }
}
