package by.gomselmash.aspiski.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTROL_SYSTEMS")
public class ControlSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IS_DISABLED")
    private boolean isDisabled;

    public ControlSystem() {
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
        return "ControlSystem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDisabled=" + isDisabled +
                '}';
    }
}
