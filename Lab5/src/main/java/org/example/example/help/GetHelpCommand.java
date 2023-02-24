package org.example.example.help;

import org.example.lab5.Command;

public class GetHelpCommand implements Command {

    private Instruction instruction;
    public GetHelpCommand(Instruction i){
        this.instruction = i;
    }
    @Override
    public void execute() {
        instruction.getInstruction();
    }
}
