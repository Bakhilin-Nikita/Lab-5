package lab5.parserFromJson;

import lab5.LabWork;

import java.util.HashSet;

public class Root {
    private HashSet<LabWork> labs;

    public void setLabs(HashSet<LabWork> labs) {
        this.labs = labs;
    }

    public HashSet<LabWork> getLabs() {
        return labs;
    }

    @Override
    public String toString() {
        return "Root{" +
                ", labs=" + this.labs +
                '}';
    }
}
