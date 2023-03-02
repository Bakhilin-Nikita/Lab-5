package org.example.lab5.workWithCollections;


import org.example.lab5.LabWork;
import org.example.lab5.UpdateCommand;
import org.example.lab5.parserFromJson.Root;

import java.io.IOException;

public class UpdateElementCommand implements UpdateCommand {
    private Root root;

    public UpdateElementCommand(Root root){
        this.root = root;
    }

    @Override
    public void execute(int id, String field) {
        try {
            root.update(id, field);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
