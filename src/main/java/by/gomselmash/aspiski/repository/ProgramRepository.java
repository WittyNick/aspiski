package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findAllByOrderByPartNumberAsc();

    List<Program> findAllByDateIsBetweenOrderByDateAscDeveloperAscMachine_MachineType_NameAsc(LocalDate from, LocalDate to);

    @Lock(LockModeType.PESSIMISTIC_READ)
    boolean existsByProgramNumberIgnoreCase(String programNumber);

    @Lock(LockModeType.PESSIMISTIC_READ)
    boolean existsByWorkshop_Id(Long workshopId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    boolean existsByDeveloper_Id(Long developerId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    boolean existsByMachine_Id(Long machineId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    boolean existsByControlSystem_Id(Long controlSystemId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void deleteById(Long id);
}
