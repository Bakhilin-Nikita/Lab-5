package command.commands.noInputCommands;

import command.Command;
import command.HelperController;

public class MaxByAuthorCommand implements Command {
    private HelperController helperController;

    public MaxByAuthorCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute() {
        helperController.maxByAuthor();
    }
}
