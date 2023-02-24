package org.example.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class Clear {
    private Command clearUpCommand;

    public Clear(Command clearUpCommand){
        this.clearUpCommand = clearUpCommand;
    }

    public void clear(){
        clearUpCommand.execute();
    }
}
