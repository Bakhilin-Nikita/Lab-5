package server7.commands.noInputCommands;

import server7.Command;
import server7.manager.HelperController;

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
