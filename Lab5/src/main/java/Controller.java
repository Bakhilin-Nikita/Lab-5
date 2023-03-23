// управляет всеми командами этого приложения


import command.HelperController;
import command.commands.ExecuteScript;
import command.commands.Invoker;
import command.commands.noInputCommands.Exit;
import command.commands.noInputCommands.help.GetHelpCommand;
import command.commands.noInputCommands.help.Information;
import command.inputCmdCollection.InputCommands;
import command.noInputCmdCollection.NoInputCommands;
import object.LabWork;
import org.apache.commons.lang3.text.WordUtils;
import parser.Root;
import parser.parserFromJson.ParserFromJson;
import parser.parserToJson.ParserToJson;

import java.io.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Controller {

    // Этот мап для команд БЕЗ входных данных
    Map<String, Invoker> commands = new HashMap<>();

    //А этот мап для команд С входными данными
    Map<String, Invoker> inputCommands = new HashMap<>();
    private HashSet<LabWork> labWorks = new HashSet<>(); // Коллекция объектов
    private ParserFromJson parserFromJson = new ParserFromJson(); // Парсинг в коллекцию


    private GetHelpCommand help = new GetHelpCommand(new Information()); // Экземпляр класса help
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // поток ввода данных с консоли

    private HelperController helperController = new HelperController();

    private Root root;

    private ExecuteScript executeScript;

    private  ArrayList<String> paths = new ArrayList<>();

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
     *
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
        }
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    /**
     * В параметры метода передается переменная типа String
     * Цикл foreach проходит по каждому обьекту коллекции commandArrayList, чтобы найти нужную команду
     * Если команда не найдена, возвращается команда Help
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
            String commandKey = "";
            String commandValue = "";
            if (cmd.contains(" ")) {
                String[] arr = cmd.split(" ", 2);

                    commandKey = arr[0];
                    commandValue = arr[1];

            } else {
                commandKey = cmd;
            }
            String key = entry.getKey();
            //String commandKey = cmd.replaceAll("\\d","");
            if (commandKey.equals(key)) {
                System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                entry.getValue().doCommand(commandValue);
            }
        }
    }







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
}