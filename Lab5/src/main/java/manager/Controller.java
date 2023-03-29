package manager;

import command.commands.*;
import command.commands.noInputCommands.help.*;
import command.inputCmdCollection.*;
import command.noInputCmdCollection.NoInputCommands;
import object.LabWork;
import org.apache.commons.lang3.text.WordUtils;
import parser.Root;
import parser.parserFromJson.ParserFromJson;
import java.io.*;
import java.util.*;

/**
 * @see Controller нужен для вызова команд. Из него уже происходит вся работа программы.
 * Ключевой класс программы.
 */

public class Controller {
    private Map<String, Invoker> commands = new HashMap<>(); // Map для команд БЕЗ входных данных, не может быть null
    private Map<String, Invoker> inputCommands = new HashMap<>(); // Map для команд С входными данными, не может быть null
    private HashSet<LabWork> labWorks = new HashSet<>(); // Коллекция объектов, не может быть null
    private ParserFromJson parserFromJson = new ParserFromJson(); // Парсинг в коллекцию. Не может быть null
    private GetHelpCommand help = new GetHelpCommand(new Information()); // Не может быть null
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  // Не может быть null
    private HelperController helperController = new HelperController(); // Не может быть null
    private Root root; // Не может быть null
    private ExecuteScript executeScript; // Не может быть null


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
        }
    }

    /**
     * Самый главный метод класса, а может и всей программы.
     * Сперва в методе запускается статический метод help.execute
     * Переменная flag нужна чтобы контролировать цикл while
     * Проверяется наличие execute_script на вводе
     * @throws IOException
     */
    public void start() throws IOException {
        setExecuteScript(new ExecuteScript(getHelperController()));
        boolean flag = false;
        help.execute();
        while (!flag) {
            String cmd = reformatCmd(reader.readLine());
            String[] arr = cmd.split(" ", 2);
            if (arr[0].equals("execute_script")) {
                getExecuteScript().execute(arr[1]);
            }
            searchCommandInCollection(cmd);

            System.out.println("---------------------");
            System.out.println("? Если возникли трудности, введите команду help");
        }
    }


    /**
     * В параметры метода передается переменная типа String
     * Цикл foreach проходит по каждому обьекту коллекции commandArrayList, чтобы найти нужную команду
     *
     * @param
     */
    public void searchCommandInCollection(String cmd) throws FileNotFoundException {

        getHelperController().setReader(new BufferedReader(new InputStreamReader(System.in)));

        NoInputCommands noInputCommands = new NoInputCommands(helperController);
        setCommands(noInputCommands.getCommands());

        InputCommands inputCommands = new InputCommands(helperController);
        setInputCommands(inputCommands.getInputCommands());


        //  No input commands
        for (Map.Entry<String, Invoker> entry : getCommands().entrySet()) {
            String key = entry.getKey();
            if (cmd.equals(key)) {
                System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                entry.getValue().doCommand(cmd);
            }
        }

        //если не было совпадений в первом мапе, пробегаемся по мапу команд с аргументами
        for (Map.Entry<String, Invoker> entry : getInputCommands().entrySet()) {
            String commandValue = "";
            String commandKey = "";
            if (cmd.contains(" ")) {
                String[] arr = cmd.split(" ", 2);

                commandKey = arr[0];
                commandValue = arr[1];

            } else {
                commandKey = cmd;
            }
            String key = entry.getKey();
            if (commandKey.equals(key)) {
                System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                entry.getValue().doCommand(commandValue);
            }
        }
    }

    /**
     * Метод форматирует введенные данные, и преобразовывает в нужную форму.
     * @param cmd
     * @return
     */
    private String reformatCmd(String cmd) {
        if (cmd != null && !checkOnExecuteScript(cmd)) {
            if (cmd.contains(" ")) {
                String[] arr = cmd.split(" ", 2);
                cmd = arr[0].replaceAll("_", " ");
                cmd = WordUtils.capitalize(cmd);
                cmd = cmd.replaceAll(" ", "");
                cmd = cmd.concat(" " + arr[1]);
            } else {
                cmd = cmd.replaceAll("_", " ");
                cmd = WordUtils.capitalize(cmd);
                cmd = cmd.replaceAll(" ", "");
            }
        } else {
            return cmd;
        }
        return cmd;
    }

    /**
     * Метод проверяет наличие в введенных данных команду execute_script
     * Если execute_script, то выкидывается true, иначе false.
     */
    private boolean checkOnExecuteScript(String cmd) {
        if (cmd != null) {
            String[] arr = cmd.split(" ", 2);
            return Objects.equals(arr[0], "execute_script");
        }

        return false;
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

    public HelperController getHelperController() {
        return helperController;
    }

    public ExecuteScript getExecuteScript() {
        return executeScript;
    }

    public void setExecuteScript(ExecuteScript executeScript) {
        this.executeScript = executeScript;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "commands=" + commands +
                ", inputCommands=" + inputCommands +
                ", labWorks=" + labWorks +
                ", parserFromJson=" + parserFromJson +
                ", help=" + help +
                ", reader=" + reader +
                ", helperController=" + helperController +
                ", root=" + root +
                ", executeScript=" + executeScript +
                '}';
    }
}