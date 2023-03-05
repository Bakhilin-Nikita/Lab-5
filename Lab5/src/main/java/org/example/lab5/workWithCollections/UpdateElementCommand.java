package org.example.lab5.workWithCollections;


import org.example.lab5.LabWork;
import org.example.lab5.UpdateCommand;
import org.example.lab5.parserFromJson.Root;

import java.io.IOException;

//все команды наследуются от 4 интерфейсов: в основном это Command, реже - ElementCommand, IDCommand и UpdateCommand.
// В данном случае имплементируем UpdateCommand.
// Все эти 4 интерфейса, по факту, одни и те же. Имеют лишь 1 метод - execute, просто у каждого разные входные параматеры.
public class UpdateElementCommand implements UpdateCommand {
    private Root root;
    //создаёи команду, котораыя будет действовать на заданный корневой объект
    public UpdateElementCommand(Root root){
        this.root = root;
    }

    //переопределяем execute по схеме: root.[нужный нам метод]
    // Если у команды есть входные данные, не забываем про исключения.
    @Override
    public void execute(int id, String field) {
        try {
            root.update(id, field);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
