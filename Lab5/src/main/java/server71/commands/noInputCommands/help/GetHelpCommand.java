package server71.commands.noInputCommands.help;


import server.Command;
import server.commands.noInputCommands.help.Information;
import server.manager.HelperController;

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
