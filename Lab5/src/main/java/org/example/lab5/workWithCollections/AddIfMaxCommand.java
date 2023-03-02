package org.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;
import org.example.lab5.parserFromJson.Root;

import java.io.IOException;

public class AddIfMaxCommand implements ElementCommand {
    private Root root;

    public AddIfMaxCommand(Root root){
        this.root = root;
    }

    @Override
    public void execute(String e) {
        try {
            root.addIfMax(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
