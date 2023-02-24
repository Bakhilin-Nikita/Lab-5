package org.example.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;

public class Add {
    private ElementCommand add;
    public Add(ElementCommand add){
        this.add = add;
    }

    public void add(LabWork e){
        add.execute(e);
    }
}
