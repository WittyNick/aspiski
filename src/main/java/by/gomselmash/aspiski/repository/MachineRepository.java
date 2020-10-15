package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {
    List<Machine> findAllByOrderByNameAsc();
}
