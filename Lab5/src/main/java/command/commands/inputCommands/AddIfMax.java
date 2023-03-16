package command.commands.inputCommands;


import command.ElementCommand;
import command.commands.Invoker;
import object.LabWork;

public class AddIfMax extends Invoker {
    private ElementCommand addMax;
    private static final String COMMAND_NAME = AddIfMax.class.getSimpleName();
  //  private static final String regex = "\\w*";


    public static String getCommandName() {
        return COMMAND_NAME;
    }

    public AddIfMax(ElementCommand addMax){
        this.addMax = addMax;
    }

    public void addIfMax(LabWork e){
        // addMax.execute(e);
    }

    @Override
    public void doCommand(String e) {
        addMax.execute(e);
    }
}
