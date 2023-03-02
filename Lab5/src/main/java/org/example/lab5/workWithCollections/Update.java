package org.example.lab5.workWithCollections;

import org.example.lab5.LabWork;
import org.example.lab5.UpdateCommand;

public class Update extends Invoker {
    public UpdateCommand updateEl;
    private static final String COMMAND_NAME = "update";
    private static final String regex = "\\d*\\s\\w*";

    public static String getCommandName() {
        return COMMAND_NAME+" "+regex;
    }
    public String getRegex() {
        return regex;
    }

    public Update(UpdateCommand updateEl) {
        this.updateEl = updateEl;
    }

    @Override
    public void doCommand(String s) {
        String[] strings = s.split(" ");
        int id = Integer.parseInt(strings[0].trim());
        String field = strings[strings.length-1];
        updateEl.execute(id,field);
    }
}
