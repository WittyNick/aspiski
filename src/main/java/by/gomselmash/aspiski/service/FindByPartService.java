package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindByPartService {
    private final ProgramRepository repository;

    public FindByPartService(ProgramRepository repository) {
        this.repository = repository;
    }

    public List<Program> findAllPrograms() {
        return repository.findAll();
    }

    public void deleteProgramById(int id) {
        repository.deleteById(id);
    }
}
