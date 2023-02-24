package org.example.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;

public class AddIfMax {
    private ElementCommand addMax;

    public AddIfMax(ElementCommand addMax){
        this.addMax = addMax;
    }

    public void addIfMax(LabWork e){
        addMax.execute(e);
    }
}
