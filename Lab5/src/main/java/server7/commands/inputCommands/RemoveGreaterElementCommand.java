package server7.commands.inputCommands;


import server7.Command;
import server7.manager.HelperController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class RemoveGreaterElementCommand implements Command {
    private HelperController helperController;

    public RemoveGreaterElementCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    @Override
    public void execute() {
        try {
            helperController.removeGreater();
        } catch (IOException | ParseException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

