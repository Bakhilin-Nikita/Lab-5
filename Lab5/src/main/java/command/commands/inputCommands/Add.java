package command.commands.inputCommands;

import command.ElementCommand;
import command.commands.Invoker;

import java.io.IOException;
import java.text.ParseException;

public class Add extends Invoker {

    private static final String COMMAND_NAME = Add.class.getSimpleName();

    private ElementCommand add;

    public Add(ElementCommand elementCommand){
        this.add = elementCommand;
    }

    public static String getCommandName() {
        return COMMAND_NAME;
    }


    @Override
    public void doCommand(String e)  {
       add.execute(e);
    }
}
