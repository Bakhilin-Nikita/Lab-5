package org.example.lab5.commands;

import org.example.lab5.Command;
import org.example.lab5.parserFromJson.Root;

public class PrintSortedTiWCommand implements Command {
    private Root root;

    public PrintSortedTiWCommand(Root root){
        this.root = root;
    }

    @Override
    public void execute() {
        root.printFieldAscendingTunedInWorks();
    }
}
