// управляет всеми командами этого приложения


import command.HelperController;
import command.commands.Invoker;
import command.commands.noInputCommands.Exit;
import command.commands.noInputCommands.help.GetHelpCommand;
import command.commands.noInputCommands.help.Help;
import command.commands.noInputCommands.help.Information;
import command.inputCmdCollection.InputCommands;
import command.noInputCmdCollection.NoInputCommands;
import object.LabWork;
import org.apache.commons.lang3.text.WordUtils;
import parser.Root;
import parser.parserFromJson.ParserFromJson;
import parser.parserToJson.ParserToJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Controller {

    // Этот мап для команд БЕЗ входных данных
    Map<String, Invoker> commands = new HashMap<>();

    //А этот мап для команд С входными данными
    Map<String, Invoker> inputCommands = new HashMap<>();
    private HashSet<LabWork> labWorks = new HashSet<>(); // Коллекция объектов
    private ParserFromJson parserFromJson = new ParserFromJson(); // Парсинг в коллекцию
    private ParserToJson parserToJson = new ParserToJson(); // Сериализация в json-файл

    private GetHelpCommand help = new GetHelpCommand(new Information()); // Экземпляр класса help
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // поток ввода данных с консоли

    private HelperController helperController = new HelperController();

    private Root root;


    /**
     * В конструкторе происходит автоматическая проверка json-файла.
     * Если в файле есть хотя бы один обьект класса LabWork он подгружается в коллекцию LabWorks класса
     *
     * @throws FileNotFoundException
     * @see LabWork
     */
    public Controller() throws IOException {
        if (parserFromJson.checkOnEmpty()) {
            root = parserFromJson.parse();
            labWorks = root.getLabWorkSet();

            //parserToJson.serialization(labWorks);
        }
    }

    /**
     * Самый главный метод класса, а может и всей программы.
     * Сперва в методе запускается статический метод help.execute
     * Переменная flag нужна чтобы контролировать цикл while
     *
     * @throws IOException
     */
    public void start() throws IOException {
        boolean flag = false;
        help.execute();
        while (!flag) {
            String cmd = reformatCmd(reader.readLine()).trim();
            if (cmd.equals(Exit.class.getSimpleName()))
                flag = true;
            //System.out.println("Command " + cmd);
            searchCommandInCollection(cmd);

        }
    }

    /**
     * В параметры метода передается переменная типа String
     * Цикл foreach проходит по каждому обьекту коллекции commandArrayList, чтобы найти нужную команду
     * Если команда не найдена, возвращается команда Help
     *
     * @param command
     *
     */
    public void searchCommandInCollection(String command) throws FileNotFoundException {
        NoInputCommands noInputCommands = new NoInputCommands(helperController);
        setCommands(noInputCommands.getCommands());

        InputCommands inputCommands = new InputCommands(helperController);
        setInputCommands(inputCommands.getInputCommands());

        // No input commands
        for (Map.Entry<String, Invoker> entry : getCommands().entrySet()) {
            String key = entry.getKey();
            if (command.equals(key)) {
                System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                entry.getValue().doCommand(command);
            }
        }

        //если не было совпадений в первом мапе, пробегаемся по мапу команд с аргументами
        for (Map.Entry<String, Invoker> entry : getInputCommands().entrySet()) {
            String key = entry.getKey();
            String commandKey = command.replaceAll("\\d","");
            if (commandKey.equals(key)) {
                System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                entry.getValue().doCommand(command);
            }
        }

    }


    public void setLabWorks(HashSet<LabWork> labWorks) {
        this.labWorks = labWorks;
    }

    public Set<LabWork> getLabWorks() {
        return labWorks;
    }

    private String reformatCmd(String cmd) {
        cmd = cmd.replaceAll("_", " ");
        cmd = WordUtils.capitalize(cmd).trim();
        cmd = cmd.replaceAll(" ", "");
        return cmd;
    }

    public void setCommands(Map<String, Invoker> commands) {
        this.commands = commands;
    }

    public Map<String, Invoker> getCommands() {
        return commands;
    }

    public void setInputCommands(Map<String, Invoker> inputCommands) {
        this.inputCommands = inputCommands;
    }

    public Map<String, Invoker> getInputCommands() {
        return inputCommands;
    }
}
