package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
}
