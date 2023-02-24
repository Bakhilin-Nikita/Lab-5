package org.example.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;

public class RemoveGreater {
    private ElementCommand removeGreaterEl;

    public RemoveGreater(ElementCommand removeGreaterEl) {
        this.removeGreaterEl = removeGreaterEl;
    }

    public void removeGreater(LabWork e){
        removeGreaterEl.execute(e);
    }
}

