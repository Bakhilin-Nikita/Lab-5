package org.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.parserFromJson.Root;

import java.io.IOException;

public class AddNewElementCommand implements ElementCommand {
    private Root root;
    public AddNewElementCommand(Root root){
        this.root = root;
    }

    public void execute() {}

    public void execute(String e) {
        try {
            root.addElement(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
