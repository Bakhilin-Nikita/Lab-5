package command.commands.inputCommands;

import command.ElementCommand;
import command.HelperController;

import java.io.IOException;
import java.text.ParseException;

public class AddIfMaxCommand implements ElementCommand {
    private HelperController helperController;

    public AddIfMaxCommand(HelperController helperController) {
        this.helperController = helperController;

    }

    @Override
    public void execute(String e) {
        try {
            helperController.addIfMax(e);
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
