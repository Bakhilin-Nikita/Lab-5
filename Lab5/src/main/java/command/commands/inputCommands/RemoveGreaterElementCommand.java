package command.commands.inputCommands;


import command.Command;
import command.ElementCommand;
import manager.HelperController;

public class RemoveGreaterElementCommand implements Command {
    private HelperController helperController;

    public RemoveGreaterElementCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute() {
        helperController.removeGreater();
    }
}

