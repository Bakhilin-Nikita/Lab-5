package command.commands.inputCommands;

import command.ElementCommand;
import command.HelperController;

import java.io.IOException;
import java.text.ParseException;

public class AddNewElementCommand implements ElementCommand {
    private HelperController helperController;

    public AddNewElementCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute(String e) {
        try {
            helperController.addElement(e);
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
