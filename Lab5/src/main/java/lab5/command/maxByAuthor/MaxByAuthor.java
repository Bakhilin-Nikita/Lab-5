package lab5.command.maxByAuthor;


import lab5.command.Command;
import lab5.features.LabWork;
import lab5.parser.Root;

import java.util.HashSet;

public class MaxByAuthor implements Command {
    private HashSet<LabWork> labs;
    private Root root;

    public MaxByAuthor(HashSet<LabWork> labs){
        this.labs = labs;
        this.root = new Root(labs);
    }

    @Override
    public void execute() {
        root.maxByAuthor();
    }
}
