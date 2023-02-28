package lab5.command.save;

import lab5.command.Command;
import lab5.parser.Root;
import lab5.parser.parserFromJson.ParserFromJson;
import lab5.parser.parserToJson.ParserToJson;

public class Save implements Command {

    private ParserToJson parserToJson = new ParserToJson();

    public Save() {
    }

    @Override
    public void execute() {
        ParserFromJson parserFromJson = new ParserFromJson();
        Root root = parserFromJson.parse();
        if (parserToJson.serialization(root.getLabs()))
            System.out.println("Коллекция успешно сохранена в файл!");
    }
}
