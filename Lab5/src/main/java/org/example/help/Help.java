package org.example.help;

import org.example.lab5.Command;
import org.example.lab5.workWithCollections.Invoker;

public class Help extends Invoker {
    private Command getHelpCommand;
    private static final String COMMAND_NAME = "help";
    private static final String regex = null;

    public static String getCommandName() {
        return COMMAND_NAME;
    }
    public String getRegex() {
        return regex;
    }
    public Help(Command getHelpCommand){
        this.getHelpCommand = getHelpCommand;
    }

    public void getHelp(){
        getHelpCommand.execute();
    }

    @Override
    public void doCommand(String s) {
        getHelpCommand.execute();
    }
}
