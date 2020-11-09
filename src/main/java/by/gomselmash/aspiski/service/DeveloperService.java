package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Developer;
import by.gomselmash.aspiski.repository.DeveloperRepository;
import by.gomselmash.aspiski.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final ProgramRepository programRepository;
    private final LogInService logInService;

    public DeveloperService(DeveloperRepository developerRepository,
                            ProgramRepository programRepository,
                            LogInService logInService) {
        this.developerRepository = developerRepository;
        this.programRepository = programRepository;
        this.logInService = logInService;
    }

    @Transactional(readOnly = true)
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAllByOrderByNameAsc();
    }

    public Developer saveDeveloper(Developer developer) {
        String name = developer.getName();
        if (developerRepository.existsByNameIgnoreCase(name)) {
            return developer;
        }
        return developerRepository.save(developer);
    }

    @Transactional
    public boolean updateDeveloper(Developer developer) {
        Long id = developer.getId();
        if (developerRepository.existsById(id)) {
            developerRepository.save(developer);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteDeveloperById(Long id) {
        boolean isExists = developerRepository.existsById(id);
        if (!isExists) {
            return false;
        }
        boolean isRelatedKey = programRepository.existsByDeveloper_Id(id);
        if (isRelatedKey) {
            return false;
        }
        developerRepository.deleteById(id);
        return true;
    }

    public String getAuthority() {
        String authority = "ROLE_ADMIN";
        if (logInService.isAuthorizationDisabled()) {
            authority = "DISABLED";
        }
        return authority;
    }
}
