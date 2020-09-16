package by.gomselmash.aspiski.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTROL_SYSTEMS")
public class ControlSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
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

    @Override
    public String toString() {
        return "ControlSystem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
