package by.gomselmash.aspiski.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTROL_SYSTEMS")
public class ControlSystem {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
