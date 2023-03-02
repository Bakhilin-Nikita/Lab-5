package org.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class Clear extends Invoker {
    private Command clearUpCommand;
    private static final String COMMAND_NAME = "clear";
    private static final String regex = null;

    public static String getCommandName() {
        return COMMAND_NAME;
    }
    public String getRegex() {
        return regex;
    }

    public Clear(Command clearUpCommand){
        this.clearUpCommand = clearUpCommand;
    }

    public void clear(){
        clearUpCommand.execute();
    }

    @Override
    public void doCommand(String s) {
        clearUpCommand.execute();
    }
}
