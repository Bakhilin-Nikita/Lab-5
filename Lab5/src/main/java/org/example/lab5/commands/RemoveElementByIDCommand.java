package org.example.lab5.commands;

import org.example.lab5.IDCommand;
import org.example.lab5.parserFromJson.Root;

public class RemoveElementByIDCommand implements IDCommand {

    private Root root;

    public RemoveElementByIDCommand(Root root){
        this.root = root;
    }

    @Override
    public void execute(int id) {
        root.removeEl(id);
    }
}
