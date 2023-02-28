package lab5;

// управляет всеми командами этого приложения

import lab5.command.Command;
import lab5.command.CommandsList;
import lab5.command.exit.Exit;
import lab5.command.help.GetHelpCommand;
import lab5.features.LabWork;
import lab5.parser.Root;
import lab5.parser.parserFromJson.ParserFromJson;
import lab5.parser.parserToJson.ParserToJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Controller {

    private HashSet<LabWork> labWorks = new HashSet<>(); // Коллекция объектов
    private ParserFromJson parserFromJson = new ParserFromJson(); // Парсинг в коллекцию
    private ParserToJson parserToJson = new ParserToJson(); // Сериализация в json-файл

    private GetHelpCommand help = new GetHelpCommand(); // Экземпляр класса help
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // поток ввода данных с консоли

    private ArrayList<Command> commandArrayList = CommandsList.getCommands(); // Коллекция содержит в себе все команды

    /**
     * В конструкторе происходит автоматическая проверка json-файла.
     * Если в файле есть хотя бы один обьект класса LabWork он подгружается в коллекцию LabWorks класса
     *
     * @throws FileNotFoundException
     * @see LabWork
     */
    public Controller() throws FileNotFoundException {
        if (parserFromJson.checkOnEmpty()) {
            Root root = parserFromJson.parse();
            labWorks = root.getLabs();
            parserToJson.serialization(labWorks);
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
            Command command = searchCommandInCollection(reader.readLine().trim());
            if (command.getClass().getSimpleName().equals(Exit.class.getSimpleName()))
                flag = true;
            command.execute();
        }
    }

    /**
     * В параметры метода передается переменная типа String
     * Цикл foreach проходит по каждому обьекту коллекции commandArrayList, чтобы найти нужную команду
     * Если команда не найдена, возвращается команда Help
     * @param command
     * @return
     */
    public Command searchCommandInCollection(String command) {
        for (Command cmd : commandArrayList) {
            if (cmd.getClass().getSimpleName().equals(command))
                return cmd;
        }

        return commandArrayList.get(0);
    }

    public void setCommandArrayList(ArrayList<Command> commandArrayList) {
        this.commandArrayList = commandArrayList;
    }

    public ArrayList<Command> getCommandArrayList() {
        return commandArrayList;
    }

    public void setLabWorks(HashSet<LabWork> labWorks) {
        this.labWorks = labWorks;
    }

    public HashSet<LabWork> getLabWorks() {
        return labWorks;
    }
}
