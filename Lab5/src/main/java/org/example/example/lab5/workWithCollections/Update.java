package org.example.example.lab5.workWithCollections;

import org.example.lab5.LabWork;
import org.example.lab5.UpdateCommand;

public class Update {
    public UpdateCommand updateEl;

    public Update(UpdateCommand updateEl) {
        this.updateEl = updateEl;
    }

    public void update(int id, LabWork e){
        updateEl.execute(id, e);
    }
}
