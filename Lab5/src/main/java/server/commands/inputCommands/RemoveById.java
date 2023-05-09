package server.commands.inputCommands;

import server.manager.HelperController;
import server.commands.Invoker;

import java.io.IOException;

public class RemoveById extends Invoker {

    private HelperController helperController;

    private static final String COMMAND_NAME = RemoveById.class.getSimpleName();

    public RemoveById(HelperController helperController) {
        this.helperController = helperController;
    }


    public void execute(int id) throws IOException {
        helperController.removeEl(id);
    }

    public  String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void doCommand(String e) {
        int i = Integer.parseInt(e.replaceAll("^\\D*?(-?\\d+).*$", "$1"));
        try {
            execute(i);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
