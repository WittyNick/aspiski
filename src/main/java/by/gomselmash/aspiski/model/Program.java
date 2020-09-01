package by.gomselmash.aspiski.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Program {
    @Id
    private int programId;
    private String partNumber;
    private String position;
    private String programNumber;

    @OneToOne
    private Developer developer;

    @OneToOne
    private Machine machine;
    private LocalDate date;
    private String info;
}
