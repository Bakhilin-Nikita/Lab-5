package command.commands.inputCommands;

import command.ElementCommand;
import command.commands.Invoker;

import java.io.IOException;
import java.text.ParseException;


public class RemoveGreater extends Invoker {

    private ElementCommand elementCommand;

    private static final String COMMAND_NAME = RemoveGreater.class.getSimpleName();

    public static String getCommandName() {
        return COMMAND_NAME;
    }


    public RemoveGreater(ElementCommand elementCommand) {
        this.elementCommand = elementCommand;
    }

    public void removeGreater(String e) throws IOException, ParseException {
        this.elementCommand.execute(e);
    }

    @Override
    public void doCommand(String e) throws IOException, ParseException {
        //int i = Integer.parseInt(e.replaceAll("^\\D*?(-?\\d+).*$", "$1"));
        removeGreater(e);
    }
}

