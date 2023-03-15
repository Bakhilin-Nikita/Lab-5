package command.commands.noInputCommands;

import command.HelperController;
import command.commands.Invoker;

public class Save extends Invoker {
    private HelperController helperController;

    private static final String COMMAND_NAME = Save.class.getSimpleName();

    public Save(HelperController helperController) {
        this.helperController = helperController;
    }

    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void doCommand(String e) {
        helperController.save();
    }
}
