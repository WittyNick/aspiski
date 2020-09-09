package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {
}
