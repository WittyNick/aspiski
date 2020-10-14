package by.gomselmash.aspiski.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "MACHINES")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MACHINE_TYPE_ID", nullable = false)
    private MachineType machineType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", machineType=" + machineType +
                '}';
    }
}
