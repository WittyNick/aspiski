package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Program;
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

    @Transactional
    public List<Program> getProgramsBetweenDates(LocalDate from, LocalDate to) {
        return programRepository.findAllByDateIsBetweenOrderByPartNumberAsc(from, to);
    }

    public List<Program> getProgramsThisMonth() {
        LocalDate to = LocalDate.now();
        LocalDate from = LocalDate.of(to.getYear(), to.getMonth(), 1);
        return getProgramsBetweenDates(from, to);
    }
}
