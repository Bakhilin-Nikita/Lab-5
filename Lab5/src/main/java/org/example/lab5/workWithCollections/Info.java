package org.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class Info extends Invoker {

    private Command getInfoCommand;
    private static final String COMMAND_NAME = "info";
    private static final String regex = null;

    public Info(Command getInfoCommand) {
        this.getInfoCommand = getInfoCommand;
    }

    public static String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void doCommand(String s) {
        getInfoCommand.execute();
    }

    @Override
    public String getRegex() {
        return null;
    }
}
