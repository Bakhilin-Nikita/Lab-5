package org.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.parserFromJson.Root;

public class AddIfMaxCommand implements ElementCommand {
    private Root root;

    public AddIfMaxCommand(Root root){
        this.root = root;
    }

    @Override
    public void execute(String e) {
        //root.addIfMax(e);
    }
}
