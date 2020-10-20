package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {
    List<Workshop> findAllByOrderByNameAsc();
    boolean existsByNameIgnoreCase(String name);
}
