package command.commands.inputCommands;

import command.ElementCommand;
import command.commands.Invoker;


public class RemoveGreater extends Invoker {

    private ElementCommand elementCommand;

    private static final String COMMAND_NAME = RemoveGreater.class.getSimpleName();

    public static String getCommandName() {
        return COMMAND_NAME;
    }


    public RemoveGreater(ElementCommand elementCommand) {
        this.elementCommand = elementCommand;
    }

    public void removeGreater(String e){
        this.elementCommand.execute(e);
    }

    @Override
    public void doCommand(String e) {
        //int i = Integer.parseInt(e.replaceAll("^\\D*?(-?\\d+).*$", "$1"));
        removeGreater(e);
    }
}

