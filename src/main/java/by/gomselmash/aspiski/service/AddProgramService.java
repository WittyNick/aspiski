package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddProgramService {
    private final ProgramRepository programRepository;

    public AddProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> findAllPrograms() {
        return programRepository.findAll();
    }

    public Program saveProgram(Program programToSave) {
        return programRepository.save(programToSave);
    }
}
