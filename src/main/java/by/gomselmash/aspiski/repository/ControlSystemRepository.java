package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.ControlSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlSystemRepository extends JpaRepository<ControlSystem, Long> {
    List<ControlSystem> findAllByOrderByNameAsc();
    boolean existsByNameIgnoreCase(String name);
}
