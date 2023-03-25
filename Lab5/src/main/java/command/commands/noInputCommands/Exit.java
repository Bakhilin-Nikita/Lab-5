package command.commands.noInputCommands;

import command.HelperController;
import command.commands.ExecuteScript;
import command.commands.Invoker;
import command.commands.noInputCommands.help.Help;

public class Exit extends Invoker {
    private static final String COMMAND_NAME = Exit.class.getSimpleName();
    private HelperController helperController;
    public Exit(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void doCommand(String e) {
        execute();
    }

    public void execute(){
        System.exit(0);
    }

    public String getCommandName() {
        return COMMAND_NAME;
    }
}
