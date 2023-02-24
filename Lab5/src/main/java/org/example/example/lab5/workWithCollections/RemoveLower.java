package org.example.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;

public class RemoveLower {
    private ElementCommand removeLowerEl;

    public RemoveLower(ElementCommand removeLowerEl) {
        this.removeLowerEl = removeLowerEl;
    }

    public void removeLower(LabWork e) {
        removeLowerEl.execute(e);
    }
}
