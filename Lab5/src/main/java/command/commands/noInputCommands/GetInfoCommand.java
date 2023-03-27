package command.commands.noInputCommands;

import command.Command;
import manager.HelperController;

public class GetInfoCommand implements Command {
    private HelperController helperController;

    public GetInfoCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute() {
        this.helperController.getInfo();
    }
}
