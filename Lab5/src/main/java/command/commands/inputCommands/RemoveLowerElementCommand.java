package command.commands.inputCommands;


import command.ElementCommand;
import command.HelperController;
import parser.Root;

public class RemoveLowerElementCommand implements ElementCommand {
    private HelperController helperController;

    public RemoveLowerElementCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute(String e) {
        helperController.removeLower(e);
    }

}
