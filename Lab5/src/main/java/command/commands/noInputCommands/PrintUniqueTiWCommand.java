package command.commands.noInputCommands;


import command.Command;
import command.HelperController;
import parser.Root;

public class PrintUniqueTiWCommand implements Command {
    private HelperController helperController;

    public PrintUniqueTiWCommand(HelperController helperController) {
        this.helperController = helperController;
    }
    @Override
    public void execute() {
        helperController.printUniqueTunedInWorks();
    }
}
