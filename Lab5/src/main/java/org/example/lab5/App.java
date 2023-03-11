package org.example.lab5;

import org.example.help.GetHelpCommand;
import org.example.help.Help;
import org.example.help.Information;
import org.example.lab5.exceptions.InvalidFieldY;
import org.example.lab5.exceptions.NullX;
import org.example.lab5.exceptions.WrongCommandInputException;
import org.example.lab5.parserFromJson.Root;
import org.example.lab5.parserToJson.ParserToJson;
import org.example.lab5.commands.*;

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

        //создаём новый корневой элемент
        Root root = new Root();
        //достаём из него коллекцию, чтобы было легче экспериментировать
        Set<LabWork> set = root.getLabWorkSet();

        //to Json
        ParserToJson inJson = new ParserToJson();
        //элементы
        LabWork lab1 = inJson.createLabWork("Rim", 12, 12, "EASY", 11, 12.3, "Nikita", "GREEN", 224, "23-05-2004");
        LabWork lab2 = inJson.createLabWork("Rim21", 123, 121, "EASY", 11, 12.3, "George", "GREEN", 224, "23-05-2004");
        LabWork lab3 = inJson.createLabWork("Ri342m21", 123423, 4312, "EASY", 11, 12.3, "Daniel", "GREEN", 224, "23-05-2004");
        //создаём команды и объекты всех наших команд
        Command getHelp = new GetHelpCommand(new Information());
        Help help = new Help(getHelp);

        Command getInfo = new GetInfoCommand(root);
        Info info = new Info(getInfo);

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

        // добавляем в коллекцию парочку элементов для экспериментов
        set.add(lab1);
        set.add(lab2);
        set.add(lab3);

        //А вот теперь внимательно!
        //создаём 2 мапа. В качестве ключа мы берём строку - это имя нашей команды. В качестве значения берётся объект-инвокер.
        // В каждом классе-инвокере прописано имя команы как константа. Его мы и достаём с помощью getCommandName().
        // В командах с входными данными помимо константы имя команды образуется с прибавлением регулярного выражения. Поэтому и мапа 2.
        // Все объекты-инвокеры наследуются от общего класса Invoker, что будет дальше упрощать нам задачу.

        // Этот мап для команд БЕЗ входных данных
        Map<String, Invoker> commands = new HashMap<>();
        //добавляем сюда все команды без входных элементов
        commands.put(help.getCommandName(),help);
        commands.put(c.getCommandName(),c);
        commands.put(unique.getCommandName(),unique);
        commands.put(sort.getCommandName(),sort);
        commands.put(author.getCommandName(),author);
        commands.put(s.getCommandName(),s);
        commands.put(info.getCommandName(),info);
        commands.put(r.getCommandName(),r);

        //А этот мап для команд С входными данными
        Map<String, Invoker> inputCommands = new HashMap<>();
        //добавляем сюда все команды с входными данными
        //inputCommands.put(r.getCommandName(),r);
        inputCommands.put(a.getCommandName(),a);
        inputCommands.put(addMax.getCommandName(),addMax);
        inputCommands.put(greater.getCommandName(),greater);
        inputCommands.put(lower.getCommandName(),lower);
        inputCommands.put(update.getCommandName(),update);

        //Сигнал о том, что команда готова к вводу данных
        System.out.println("Рендер завершён");

        //пользователь вводит команду
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            // делаем флаг для прерывания цикла
            boolean flag = true;
            // в начале проходимся по мапу с командами без аргументов
            for(Map.Entry<String, Invoker> entry: commands.entrySet()) {
                //берём ключи - имена наших команд
                String key = entry.getKey();
                //программа проверяет, похожа ли строка добуквенно на то, что ввёл пользователь
                if (line.equals(key)){
                    //если то, что ввёл пользователь - команда исполняется.
                    // Как я говорил, все инвокеры наследуются от общего абстрактного класса. Там прописан метод doCommand, который принимает на вход строку.
                    // В данном случае строка будет null, но здесь это не сыграет роли.
                    entry.getValue().doCommand(line);
                    flag = false;
                    break;
                }
            }

            //если не было совпадений в первом мапе, пробегаемся по мапу команд с аргументами
            for(Map.Entry<String, Invoker> entry: inputCommands.entrySet()) {
                String key = entry.getKey();
                Invoker value = entry.getValue();
                Pattern p = Pattern.compile(key);
                //В коде выше мы взяли значение, а ключ при помощи класса Pattern мы сделали шаблоном для регулярного выражения
                Matcher matcher = p.matcher(line);
                //программа сравнивает введённую строку с шаблоном
                if (matcher.find()) {
                    //если строка совпала с шаблоном мы разделяем её на массив с 2 элементами. Делим только по первому пробелу
                    String[] strings = line.split(" ", 2);
                    //в качестве аргумента для метода берём вторую часть строки - это и есть входной параметр
                    value.doCommand(strings[strings.length - 1]);
                    flag = false;
                    break;
                }
            }

            if (flag){
                //если что-то не так: исключение
                throw new WrongCommandInputException("Команда введена неверно!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //показываем себе прогу на случай чего
        s.show();
    }
}
