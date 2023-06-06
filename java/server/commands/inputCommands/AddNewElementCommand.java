package server.commands.inputCommands;

import server.Command;
import server.manager.HelperController;

import java.io.IOException;
import java.text.ParseException;

public class AddNewElementCommand implements Command {
    private HelperController helperController;

    public AddNewElementCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute() {
        try {
            helperController.addElement();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
