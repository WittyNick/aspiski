package by.gomselmash.aspiski.model.entity;

public class Machine {
    private int machineId;
    private String machineName;
    private NcSystem ncSystem;
    private MachineType machineType;

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public NcSystem getNcSystem() {
        return ncSystem;
    }

    public void setNcSystem(NcSystem ncSystem) {
        this.ncSystem = ncSystem;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }
}
