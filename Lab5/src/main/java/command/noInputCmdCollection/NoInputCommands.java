package command.noInputCmdCollection;

import command.Command;
import command.HelperController;
import command.commands.*;
import command.commands.noInputCommands.*;
import command.commands.noInputCommands.ShowTheCollectionCommand;
import command.commands.noInputCommands.help.GetHelpCommand;
import command.commands.noInputCommands.help.Help;
import command.commands.noInputCommands.help.Information;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class NoInputCommands {
    private Map<String, Invoker> commands = new HashMap<>();
    private HelperController helperController;

    public NoInputCommands(HelperController helperController) throws FileNotFoundException {
        //this.root = root;
        this.helperController = helperController;

        Command getHelp = new GetHelpCommand(new Information());
        Help help = new Help(getHelp);

        Command getInfo = new GetInfoCommand(helperController);
        Info info = new Info(getInfo);

        Command showLabs = new ShowTheCollectionCommand(helperController);
        Show s = new Show(showLabs);

//        IDCommand removeEl = new RemoveElementByIDCommand(this.root);
//        Remove r = new Remove(removeEl);

        Command clearLabs = new ClearTheCollectionCommand(helperController);
        Clear c = new Clear(clearLabs);

        Command printTiW = new PrintUniqueTiWCommand(helperController);
        UniqueTiW unique = new UniqueTiW(printTiW);

        Command sortTiW = new PrintSortedTiWCommand(helperController);
        SortedTiW sort = new SortedTiW(sortTiW);

        Command maxByAuthor = new MaxByAuthorCommand(helperController);
        MaxByAuthor author = new MaxByAuthor(maxByAuthor);

        Save save = new Save(helperController);

        commands.put(help.getCommandName(), help);
        commands.put(c.getCommandName(), c);
        commands.put(unique.getCommandName(), unique);
        commands.put(sort.getCommandName(), sort);
        commands.put(author.getCommandName(), author);
        commands.put(s.getCommandName(), s);
        commands.put(info.getCommandName(), info);
        commands.put(save.getCommandName(), save);
    }

    public Map<String, Invoker> getCommands() {
        return commands;
    }
}
