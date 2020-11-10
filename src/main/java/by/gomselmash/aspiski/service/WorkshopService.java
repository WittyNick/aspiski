package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.repository.ProgramRepository;
import by.gomselmash.aspiski.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkshopService {
    private final WorkshopRepository workshopRepository;
    private final ProgramRepository programRepository;
    private final LogInService logInService;

    public WorkshopService(WorkshopRepository workshopRepository,
                           ProgramRepository programRepository,
                           LogInService logInService) {
        this.workshopRepository = workshopRepository;
        this.programRepository = programRepository;
        this.logInService = logInService;
    }

    @Transactional(readOnly = true)
    public List<Workshop> findAllWorkshops() {
        return workshopRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public Workshop saveWorkshop(Workshop workshop) {
        String name = workshop.getName();
        if (workshopRepository.existsByNameIgnoreCase(name)) {
            return workshop;
        }
        return workshopRepository.save(workshop);
    }

    @Transactional
    public boolean updateWorkshop(Workshop workshop) {
        Long id = workshop.getId();
        if (workshopRepository.existsById(id)) {
            workshopRepository.save(workshop);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteWorkshopById(Long id) {
        boolean isExists = workshopRepository.existsById(id);
        if (!isExists) {
            return false;
        }
        boolean isRelatedKey = programRepository.existsByWorkshop_Id(id);
        if (isRelatedKey) {
            return false;
        }
        workshopRepository.deleteById(id);
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
