package org.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class Show extends Invoker{
    private Command showTheSummary;
    private static final String COMMAND_NAME = "show";
    private static final String regex = null;
    public String getRegex() {
        return regex;
    }

    public static String getCommandName() {
        return COMMAND_NAME;
    }

    public Show(Command showTheSummary){
        this.showTheSummary = showTheSummary;
    }

    public void show(){
        showTheSummary.execute();
    }

    @Override
    public void doCommand(String s) {
        showTheSummary.execute();
    }
}
