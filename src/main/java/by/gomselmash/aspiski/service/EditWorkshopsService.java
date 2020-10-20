package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditWorkshopsService {
    private final WorkshopRepository workshopRepository;

    public EditWorkshopsService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public List<Workshop> findAllWorkshopsSorted() {
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

    public void deleteWorkshopById(int id) {
        workshopRepository.deleteById(id);
    }
}
