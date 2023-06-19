package server7.commands.noInputCommands;

import server7.Command;
import server7.manager.HelperController;

import java.io.IOException;

public class GetInfoCommand implements Command {
    private HelperController helperController;

    public GetInfoCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute() {
        try {
            this.helperController.getInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
