package lab5.command.help;

import lab5.command.Command;

import java.io.FileNotFoundException;

public class Help {
    private final Command getHelpCommand;

    public Help(Command getHelpCommand) {
        this.getHelpCommand = getHelpCommand;
    }

    public void getHelp() throws FileNotFoundException {
        getHelpCommand.execute();
    }
}
