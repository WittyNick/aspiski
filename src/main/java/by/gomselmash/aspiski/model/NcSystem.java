package by.gomselmash.aspiski.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NcSystem {
    @Id
    private int ncSystemId;
    private String ncSystemName;

    public int getNcSystemId() {
        return ncSystemId;
    }

    public void setNcSystemId(int ncSystemId) {
        this.ncSystemId = ncSystemId;
    }

    public String getNcSystemName() {
        return ncSystemName;
    }

    public void setNcSystemName(String ncSystemName) {
        this.ncSystemName = ncSystemName;
    }
}
