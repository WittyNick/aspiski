package by.gomselmash.aspiski.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "MACHINE_TYPES")
/*
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) -
let's Jackson to load lazy MachineType field in other entities during serialization.

There are another ways to solve the problem. Some common are:
- FetchType.LAZY -> EAGER (in class Machine)
- Use setting in application.properties spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false
to suppress Jackson serialization exception.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MachineType {
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
        return "MachineType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
