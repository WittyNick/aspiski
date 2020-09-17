package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;

@Service
public class EditProgramsService {
    private final ProgramRepository programRepository;

    public EditProgramsService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public void saveProgram(Program programToSave) {
        programRepository.save(programToSave);
    }
}
