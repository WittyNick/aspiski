package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindByPartService {
    private final ProgramRepository programRepository;

    public FindByPartService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> findAllPrograms() {
        return programRepository.findAll();
    }
}
