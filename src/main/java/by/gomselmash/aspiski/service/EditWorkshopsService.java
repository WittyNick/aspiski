package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.Workshop;
import by.gomselmash.aspiski.repository.WorkshopRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

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

    public Workshop saveWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    public void deleteWorkshopById(int id) {
        workshopRepository.count(new Example<Workshop>() {

            @Override
            public Workshop getProbe() {
                return null;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return null;
            }
        });
        workshopRepository.deleteById(id);
    }
}
