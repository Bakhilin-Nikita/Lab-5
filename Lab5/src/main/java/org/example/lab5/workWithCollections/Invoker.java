package org.example.lab5.workWithCollections;
//Это общий класс-наследник для всех инвокеров
public abstract class Invoker {
    //общий метод ля выполнения всех команд. Очень поможет нам для создания полиморфизма при вводе команд
    public abstract void doCommand(String s);
    //для регулярных выражений. Ничего особенного
    public abstract String getRegex();
}
