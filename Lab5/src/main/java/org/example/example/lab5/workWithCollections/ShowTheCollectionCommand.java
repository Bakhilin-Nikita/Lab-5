package org.example.example.lab5.workWithCollections;

import org.example.lab5.Command;
import org.example.lab5.parserFromJson.Root;

public class ShowTheCollectionCommand implements Command {
    private Root root;
    public ShowTheCollectionCommand(Root root){
        this.root = root;
    }

    public void execute() {
        root.show();
    }
}
