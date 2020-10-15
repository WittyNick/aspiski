package by.gomselmash.aspiski.service;

import by.gomselmash.aspiski.model.ControlSystem;
import by.gomselmash.aspiski.repository.ControlSystemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditControlSystemsService {
    private final ControlSystemRepository controlSystemRepository;

    public EditControlSystemsService(ControlSystemRepository controlSystemRepository) {
        this.controlSystemRepository = controlSystemRepository;
    }

    public List<ControlSystem> findAllControlSystemsSorted() {
        return controlSystemRepository.findAllByOrderByNameAsc(); // when nothing was found returns empty List
    }

    public ControlSystem saveControlSystem(ControlSystem controlSystem) {
        return controlSystemRepository.save(controlSystem);
    }

    public void deleteControlSystemById(int id) {
        controlSystemRepository.deleteById(id);
    }
}
