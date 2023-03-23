package command.commands.inputCommands;


import command.ElementCommand;
import command.commands.Invoker;
import object.LabWork;

public class AddIfMax extends Invoker {
    private ElementCommand addMax;
    private static final String COMMAND_NAME = AddIfMax.class.getSimpleName();

    public static String getCommandName() {
        return COMMAND_NAME;
    }

    public AddIfMax(ElementCommand addMax){
        this.addMax = addMax;
    }


    @Override
    public void doCommand(String e) {
        addMax.execute(e);
    }
}
