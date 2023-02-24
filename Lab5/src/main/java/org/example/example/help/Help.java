package org.example.example.help;

import org.example.lab5.Command;

public class Help {
    private Command getHelpCommand;
    public Help(Command getHelpCommand){
        this.getHelpCommand = getHelpCommand;
    }

    public void getHelp(){
        getHelpCommand.execute();
    }
}
