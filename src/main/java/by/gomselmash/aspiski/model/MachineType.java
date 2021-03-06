package by.gomselmash.aspiski.model;

import javax.persistence.*;

/*
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) -
lets Jackson to ignore lazy MachineType field in other entities during serialization.

There are another ways to solve the problem. Some common are:
- FetchType.LAZY -> EAGER (in class Machine)
- Use setting in application.properties spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false
to suppress Jackson serialization exception.
 */
@Entity
@Table(name = "MACHINE_TYPES")
public class MachineType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IS_DISABLED")
    private boolean isDisabled;

    public MachineType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean disabled) {
        this.isDisabled = disabled;
    }

    @Override
    public String toString() {
        return "MachineType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDisabled=" + isDisabled +
                '}';
    }
}
