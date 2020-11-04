package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findAllByOrderByPartNumberAsc();
    List<Program> findAllByDateIsBetweenOrderByDateAscDeveloperAscMachine_MachineType_NameAsc(LocalDate from, LocalDate to);
    boolean existsByProgramNumberIgnoreCase(String programNumber);
    boolean existsByWorkshop_Id(Long workshopId);
    boolean existsByDeveloper_Id(Long developerId);
    boolean existsByMachine_Id(Long machineId);
    boolean existsByControlSystem_Id(Long controlSystemId);
}
