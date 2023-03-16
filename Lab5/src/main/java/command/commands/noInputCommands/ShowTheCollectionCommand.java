package command.commands.noInputCommands;


import command.Command;
import command.HelperController;
import parser.Root;

public class ShowTheCollectionCommand implements Command {
    private HelperController helperController;

    public ShowTheCollectionCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    public void execute() {
        getHelperController().show();
    }

    public HelperController getHelperController() {
        return helperController;
    }
}
