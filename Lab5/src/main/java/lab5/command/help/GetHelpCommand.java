package lab5.command.help;

import lab5.command.Command;

public class GetHelpCommand implements Command {

    @Override
    public void execute() {
        Instruction.getInstruction();
    }
}
