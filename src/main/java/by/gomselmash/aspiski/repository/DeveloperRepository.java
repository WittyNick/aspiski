package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    List<Developer> findAllByOrderByNameAsc();
}
