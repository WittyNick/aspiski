package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
    List<Program> findAllByOrderByPartNumberAsc();
    boolean existsByProgramNumberIgnoreCase(String programNumber);
}
