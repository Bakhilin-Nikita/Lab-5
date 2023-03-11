package org.example.lab5.commands;

import org.example.lab5.Command;
import org.example.lab5.parserFromJson.Root;

public class GetInfoCommand implements Command {
    private Root root;

    public GetInfoCommand(Root root) {
        this.root = root;
    }

    @Override
    public void execute() {
        root.getInfo();
    }
}
