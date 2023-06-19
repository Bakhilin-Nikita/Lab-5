package server7.commands.noInputCommands;

import server7.Command;
import server7.manager.HelperController;

import java.sql.SQLException;

public class ClearTheCollectionCommand implements Command {
    private HelperController helperController;

    public ClearTheCollectionCommand(HelperController helperController) {
        this.helperController = helperController;
    }

    public void execute()
    {
        try {
            helperController.clearCollection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
