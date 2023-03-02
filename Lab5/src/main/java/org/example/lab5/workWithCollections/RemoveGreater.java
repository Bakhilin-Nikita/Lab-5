package org.example.lab5.workWithCollections;

import org.example.lab5.ElementCommand;
import org.example.lab5.LabWork;

public class RemoveGreater extends Invoker{
    private ElementCommand removeGreaterEl;
    private static final String COMMAND_NAME = "remove_greater";
    private static final String regex = "\\w*";

    public static String getCommandName() {
        return COMMAND_NAME+" "+regex;
    }
    public String getRegex() {
        return regex;
    }

    public RemoveGreater(ElementCommand removeGreaterEl) {
        this.removeGreaterEl = removeGreaterEl;
    }

    public void removeGreater(LabWork e){

    }

    @Override
    public void doCommand(String s) {
        removeGreaterEl.execute(s);
    }
}

