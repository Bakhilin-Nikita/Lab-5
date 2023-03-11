package org.example.lab5.commands;

import org.example.lab5.ElementCommand;

public class RemoveLower extends Invoker {
    private ElementCommand removeLowerEl;
    private static final String COMMAND_NAME = "remove_lower";
    private static final String regex = "\\w*";

    public static String getCommandName() {
        return COMMAND_NAME+" "+regex;
    }
    public String getRegex() {
        return regex;
    }

    public RemoveLower(ElementCommand removeLowerEl) {
        this.removeLowerEl = removeLowerEl;
    }

    public void removeLower(String e) {
        removeLowerEl.execute(e);
    }

    @Override
    public void doCommand(String s) {
        removeLowerEl.execute(s);
    }
}
