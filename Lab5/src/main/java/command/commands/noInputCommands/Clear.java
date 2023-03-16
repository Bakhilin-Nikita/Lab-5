package command.commands.noInputCommands;

import command.Command;
import command.commands.Invoker;

public class Clear extends Invoker {
    private Command clearUpCommand;
    private static final String COMMAND_NAME = Clear.class.getSimpleName();


    public static String getCommandName() {
        return COMMAND_NAME;
    }


    public Clear(Command clearUpCommand){
        this.clearUpCommand = clearUpCommand;
    }

    public void clear(){
        clearUpCommand.execute();
    }

    @Override
    public void doCommand(String e) {
        clearUpCommand.execute();
    }
}
