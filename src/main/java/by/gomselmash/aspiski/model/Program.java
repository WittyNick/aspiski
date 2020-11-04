package by.gomselmash.aspiski.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "PROGRAMS")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    /*
    In order to change the length = 1000 (VARCHAR(1000)) of the field,
    it is necessary to recreate the database again (i.e. first delete the old one).
     */
    @Column(name = "PART_NUMBER", length = 1000, nullable = false)
    private String partNumber;

    @Column(name = "PROGRAM_NUMBER", nullable = false)
    private String programNumber;

    @Column(name = "POSITION")
    private int position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WORKSHOP_ID", nullable = false)
    private Workshop workshop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEVELOPER_ID", nullable = false)
    private Developer developer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MACHINE_ID", nullable = false)
    private Machine machine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTROL_SYSTEM", nullable = false)
    private ControlSystem controlSystem;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "INFO", length = 1000)
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getProgramNumber() {
        return programNumber;
    }

    public void setProgramNumber(String programNumber) {
        this.programNumber = programNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public ControlSystem getControlSystem() {
        return controlSystem;
    }

    public void setControlSystem(ControlSystem controlSystem) {
        this.controlSystem = controlSystem;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getParsedDate(String pattern) { // pattern RU - "dd.MM.yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
}
