package org.example.lab5;

import org.example.help.GetHelpCommand;
import org.example.help.Help;
import org.example.help.Info;
import org.example.lab5.exceptions.InvalidFieldY;
import org.example.lab5.exceptions.NullX;
import org.example.lab5.exceptions.WrongCommandInputException;
import org.example.lab5.parserFromJson.Root;
import org.example.lab5.parserToJson.ParserToJson;
import org.example.lab5.workWithCollections.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kernel Class of this program by ...
 */

public class App {

    public static void main(String[] args) throws NullX, InvalidFieldY {

        Root root = new Root();
        Set<LabWork> set = root.getLabWorkSet();
        Info info = new Info();

        //to Json
        ParserToJson inJson = new ParserToJson();

        LabWork lab1 = inJson.createLabWork("Rim", 12, 12, "EASY", 11, 12.3, "Nikita", "GREEN", 224, "23-05-2004");
        LabWork lab2 = inJson.createLabWork("Rim21", 123, 121, "EASY", 11, 12.3, "George", "GREEN", 224, "23-05-2004");
        LabWork lab3 = inJson.createLabWork("Ri342m21", 123423, 4312, "EASY", 11, 12.3, "Daniel", "GREEN", 224, "23-05-2004");

        Command getHelp = new GetHelpCommand(info);
        Help help = new Help(getHelp);

        ElementCommand addEl = new AddNewElementCommand(root);
        Add a = new Add(addEl);

        Command showLabs = new ShowTheCollectionCommand(root);
        Show s = new Show(showLabs);

        IDCommand removeEl = new RemoveElementByIDCommand(root);
        Remove r = new Remove(removeEl);

        Command clearLabs = new ClearTheCollectionCommand(root);
        Clear c = new Clear(clearLabs);

        Command printTiW = new PrintUniqueTiWCommand(root);
        UniqueTiW unique = new UniqueTiW(printTiW);

        Command sortTiW = new PrintSortedTiWCommand(root);
        SortedTiW sort = new SortedTiW(sortTiW);

        Command maxByAuthor = new MaxByAuthorCommand(root);
        AuthorMaximization author = new AuthorMaximization(maxByAuthor);

        ElementCommand addElIfMax = new AddIfMaxCommand(root);
        AddIfMax addMax = new AddIfMax(addElIfMax);

        ElementCommand removeGreaterEl = new RemoveGreaterElementCommand(root);
        RemoveGreater greater = new RemoveGreater(removeGreaterEl);

        ElementCommand removeLowerEl = new RemoveLowerElementCommand(root);
        RemoveLower lower = new RemoveLower(removeLowerEl);

        UpdateCommand updateEl = new UpdateElementCommand(root);
        Update update = new Update(updateEl);

        set.add(lab1);
        set.add(lab2);
        set.add(lab3);
        //addMax.addIfMax(lab3);

        //greater.removeGreater(lab2);
        //lower.removeLower(lab2);

        //update.update(2,lab3);

        //unique.printUniqueTunedInWorks();
        //sort.printFieldAscendingTunedInWorks();
        //author.maxByAuthor();

        Map<String, Invoker> commands = new HashMap<>();
        Map<String, Invoker> inputCommands = new HashMap<>();

        commands.put(help.getCommandName(),help);
        commands.put(c.getCommandName(),c);
        commands.put(unique.getCommandName(),unique);
        commands.put(sort.getCommandName(),sort);
        commands.put(author.getCommandName(),author);
        commands.put(s.getCommandName(),s);

        inputCommands.put(r.getCommandName(),r);
        inputCommands.put(a.getCommandName(),a);
        inputCommands.put(addMax.getCommandName(),addMax);
        inputCommands.put(greater.getCommandName(),greater);
        inputCommands.put(lower.getCommandName(),lower);
        inputCommands.put(update.getCommandName(),update);

        System.out.println("Рендер завершён");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            boolean flag = true;
            for(Map.Entry<String, Invoker> entry: commands.entrySet()) {
                String key = entry.getKey();
                if (line.equals(key)){
                    entry.getValue().doCommand(line);
                    flag = false;
                    break;
                }
            }

            for(Map.Entry<String, Invoker> entry: inputCommands.entrySet()) {
                String key = entry.getKey();
                Invoker value = entry.getValue();
                Pattern p = Pattern.compile(key);
                Matcher matcher = p.matcher(line);
                if (matcher.find()) {
                    String[] strings = line.split(" ", 0);
                    value.doCommand(strings[strings.length - 1]);
                    flag = false;
                    break;
                }
            }

            if (flag){
                throw new WrongCommandInputException("Команда введена неверно!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        s.show();
    }
}
