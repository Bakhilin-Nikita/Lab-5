package command.commands.noInputCommands;

import command.Command;
import command.HelperController;
import command.commands.noInputCommands.help.Help;
import parser.Root;

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
