package by.gomselmash.aspiski.repository;

import by.gomselmash.aspiski.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
    List<Program> findAllByOrderByPartNumberAsc();
    List<Program> findAllByDateIsBetweenOrderByDateAscDeveloperAscMachine_MachineType_NameAsc(LocalDate from, LocalDate to);
    boolean existsByProgramNumberIgnoreCase(String programNumber);
    boolean existsByWorkshop_Id(int workshopId);
    boolean existsByDeveloper_Id(int developerId);
    boolean existsByMachine_Id(int machineId);
    boolean existsByControlSystem_Id(int controlSystemId);
}
