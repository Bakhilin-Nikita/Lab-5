package org.example.lab5.workWithCollections;

import org.example.lab5.Command;
import org.example.lab5.parserFromJson.Root;

public class PrintUniqueTiWCommand implements Command {
    private Root root;

    public PrintUniqueTiWCommand(Root root){
        this.root = root;
    }
    @Override
    public void execute() {
        root.printUniqueTunedInWorks();
    }
}
