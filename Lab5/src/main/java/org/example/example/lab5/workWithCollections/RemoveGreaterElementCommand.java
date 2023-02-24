package org.example.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;
import org.example.lab5.parserFromJson.Root;

public class RemoveGreaterElementCommand implements ElementCommand {
    private Root root;
    public RemoveGreaterElementCommand(Root root){
        this.root = root;
    }

    @Override
    public void execute() {}

    @Override
    public void execute(LabWork e) {
        root.removeGreater(e);
    }
}

