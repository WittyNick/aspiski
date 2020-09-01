package by.gomselmash.aspiski.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MachineType {
    @Id
    private int machineTypeId;
    private String typeName;

    public int getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(int machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
