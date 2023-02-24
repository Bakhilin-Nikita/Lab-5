package org.example.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;
import org.example.lab5.parserFromJson.Root;

public class AddNewElementCommand implements ElementCommand {
    private Root root;
    public AddNewElementCommand(Root root){
        this.root = root;
    }

    public void execute() {}

    public void execute(LabWork e) {
        root.addEl(e);
    }
}
