package by.gomselmash.aspiski.model;

import javax.persistence.*;

@Entity
@Table(name = "MACHINES")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MACHINE_TYPE_ID", nullable = false)
    private MachineType machineType;

    @Column(name = "IS_DISABLED")
    private boolean isDisabled;

    public Machine() {
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

    public void setName(String machineName) {
        this.name = machineName;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean disabled) {
        this.isDisabled = disabled;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", machineType=" + machineType +
                ", isDisabled=" + isDisabled +
                '}';
    }
}
