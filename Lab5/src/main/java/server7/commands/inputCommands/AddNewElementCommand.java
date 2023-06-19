package server7.commands.inputCommands;

import server7.Command;
import server7.manager.HelperController;

import java.io.IOException;
import java.sql.SQLException;
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
        } catch (ParseException | ClassNotFoundException | SQLException e) {
            System.out.println("Что-то пошло не так.");
        }
    }
}
