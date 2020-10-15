package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.MachineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineTypeRepository extends JpaRepository<MachineType, Integer> {
    List<MachineType> findAllByOrderByNameAsc();
}
