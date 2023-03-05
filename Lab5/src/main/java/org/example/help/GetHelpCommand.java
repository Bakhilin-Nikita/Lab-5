package org.example.help;

import org.example.lab5.Command;

public class GetHelpCommand implements Command {

    private Information instruction;
    public GetHelpCommand(Information i){
        this.instruction = i;
    }
    @Override
    public void execute() {
        instruction.getInstruction();
    }
}
