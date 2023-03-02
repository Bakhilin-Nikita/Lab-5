package org.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class SortedTiW extends Invoker{
    private Command sortTiW;
    private static final String COMMAND_NAME = "print_field_ascending_tuned_in_works";
    private static final String regex = null;

    public static String getCommandName() {
        return COMMAND_NAME;
    }
    public String getRegex() {
        return regex;
    }

    public SortedTiW(Command sortTiW){
        this.sortTiW = sortTiW;
    }

    public void printFieldAscendingTunedInWorks(){
        sortTiW.execute();
    }

    @Override
    public void doCommand(String s) {
        sortTiW.execute();
    }
}
