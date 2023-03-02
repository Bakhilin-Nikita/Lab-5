package org.example.lab5.workWithCollections;

import org.example.lab5.IDCommand;

public class Remove extends Invoker {
    private IDCommand removeElement;
    private static final String COMMAND_NAME = "remove";
    private static final String regex = "\\d*";

    public static String getCommandName() {
        return COMMAND_NAME+" "+regex;
    }
    public String getRegex() {
        return regex;
    }

    public Remove(IDCommand removeElement){
        this.removeElement = removeElement;
    }

    public void remove(int id){
        removeElement.execute(id);
    }

    @Override
    public void doCommand(String s) {
        int id = Integer.parseInt(s.trim());
        removeElement.execute(id);
    }
}