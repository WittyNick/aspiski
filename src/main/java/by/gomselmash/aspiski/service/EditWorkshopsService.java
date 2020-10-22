package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.repository.ProgramRepository;
import by.gomselmash.aspiski.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EditWorkshopsService {
    private final WorkshopRepository workshopRepository;
    private final ProgramRepository programRepository;

    public EditWorkshopsService(
            WorkshopRepository workshopRepository,
            ProgramRepository programRepository
    ) {
        this.workshopRepository = workshopRepository;
        this.programRepository = programRepository;
    }

    @Transactional(readOnly = true)
    public List<Workshop> findAllWorkshopsSorted() {
        return workshopRepository.findAllByOrderByNameAsc();
    }

    public Workshop saveWorkshop(Workshop workshop) {
        String name = workshop.getName();
        if (workshopRepository.existsByNameIgnoreCase(name)) {
            return workshop;
        }
        return workshopRepository.save(workshop);
    }

    public boolean updateWorkshop(Workshop workshop) {
        int id = workshop.getId();
        if (workshopRepository.existsById(id)) {
            workshopRepository.save(workshop);
            return true;
        }
        return false;
    }

    public boolean deleteWorkshopById(int id) {
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
}
