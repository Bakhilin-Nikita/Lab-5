package org.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class AuthorMaximization extends Invoker{
    private Command maxAuthorCommand;
    private static final String COMMAND_NAME = "max_by_author";
    private static final String regex = null;

    public static String getCommandName() {
        return COMMAND_NAME;
    }
    public String getRegex() {
        return regex;
    }

    public AuthorMaximization(Command maxAuthorCommand){
        this.maxAuthorCommand = maxAuthorCommand;
    }

    public void maxByAuthor(){
       maxAuthorCommand.execute();
    }

    @Override
    public void doCommand(String s) {
        maxAuthorCommand.execute();
    }
}
