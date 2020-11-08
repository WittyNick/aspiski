package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.ControlSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ControlSystemRepository extends JpaRepository<ControlSystem, Long> {

    List<ControlSystem> findAllByOrderByNameAsc();

    @Lock(LockModeType.PESSIMISTIC_READ)
    boolean existsByNameIgnoreCase(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void deleteById(Long id);
}
