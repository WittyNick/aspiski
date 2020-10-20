package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Developer;
import by.gomselmash.aspiski.repository.DeveloperRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditDevelopersService {
    private final DeveloperRepository developerRepository;

    public EditDevelopersService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> findAllDevelopersSorted() {
        return developerRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public Developer saveDeveloper(Developer developer) {
        String name = developer.getName();
        if (developerRepository.existsByNameIgnoreCase(name)) {
            return developer;
        }
        return developerRepository.save(developer);
    }

    public void deleteDeveloperById(int id) {
        developerRepository.deleteById(id);
    }
}
