package command.commands.inputCommands;


import command.ElementCommand;
import command.HelperController;

public class RemoveGreaterElementCommand implements ElementCommand {
    private HelperController helperController;

    public RemoveGreaterElementCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute(String e) {
        helperController.removeGreater(e);
    }
}

