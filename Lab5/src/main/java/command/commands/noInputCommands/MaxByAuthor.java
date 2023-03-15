package command.commands.noInputCommands;

import command.Command;
import command.commands.Invoker;


public class MaxByAuthor extends Invoker {
    private Command maxAuthorCommand;
    private static final String COMMAND_NAME = MaxByAuthor.class.getSimpleName();

    public static String getCommandName() {
        return COMMAND_NAME;
    }


    public MaxByAuthor(Command maxAuthorCommand){
        this.maxAuthorCommand = maxAuthorCommand;
    }

    public void maxByAuthor(){
       maxAuthorCommand.execute();
    }

    @Override
    public void doCommand(String e) {
        maxAuthorCommand.execute();
    }
}
