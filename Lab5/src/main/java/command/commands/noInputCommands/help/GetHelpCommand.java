package command.commands.noInputCommands.help;


import command.Command;

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
