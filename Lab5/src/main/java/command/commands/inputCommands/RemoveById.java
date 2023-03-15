package command.commands.inputCommands;

import command.HelperController;
import command.commands.Invoker;

public class RemoveById extends Invoker {

    private HelperController helperController;

    private static final String COMMAND_NAME = RemoveById.class.getSimpleName();

    public RemoveById(HelperController helperController) {
        this.helperController = helperController;
    }


    public void execute(int id) {
        helperController.removeEl(id);
    }

    public  String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void doCommand(String e) {
        int i = Integer.parseInt(e.replaceAll("^\\D*?(-?\\d+).*$", "$1"));
        execute(i);
    }
}
