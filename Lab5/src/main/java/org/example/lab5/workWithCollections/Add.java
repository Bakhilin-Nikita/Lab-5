package org.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;

public class Add extends Invoker {

    private ElementCommand add;
    private static final String COMMAND_NAME = "add";
    private static final String regex = "\\w*";

    public static String getCommandName() {
        return COMMAND_NAME+" "+regex;
    }
    public String getRegex() {
        return regex;
    }

    public Add(ElementCommand add){
        this.add = add;
    }

    public void add(LabWork e){
        //add.execute(e);
    }

    @Override
    public void doCommand(String s) {
        add.execute(s);
    }
}
