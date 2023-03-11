package org.example.lab5.commands;

import org.example.lab5.ElementCommand;
import org.example.lab5.parserFromJson.Root;

public class RemoveLowerElementCommand implements ElementCommand {
    private Root root;

    public RemoveLowerElementCommand(Root root) {
        this.root = root;
    }

    @Override
    public void execute(String e) {
        root.removeLower(e);
    }

}
