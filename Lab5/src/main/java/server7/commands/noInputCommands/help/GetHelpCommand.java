package server7.commands.noInputCommands.help;


import server7.commands.noInputCommands.help.Information;
import server7.Command;
import server7.manager.HelperController;

import java.io.IOException;

public class GetHelpCommand implements Command {

    private Information instruction;
    private HelperController helperController;
    public GetHelpCommand(HelperController helperController){
        this.helperController = helperController;
    }
    @Override
    public void execute() {
        try {
            helperController.getHelp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
