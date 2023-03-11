package org.example.lab5.parserFromJson;

import org.example.lab5.LabWork;
import org.example.lab5.entity.Coordinates;
import org.example.lab5.entity.Person;
import org.example.lab5.entity.enums.Color;
import org.example.lab5.entity.enums.Difficulty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static java.time.LocalDate.parse;

public class Root {

    private Set<LabWork> labWorkSet;

    //конструктор для корневого элемента, чтобы у него сразу была коллекция
    public Root() {
        this.labWorkSet = new HashSet<>();
    }

    //компаратор для сравнения элементов коллекции. В качестве элемента сравнения беру поле minimalPoint
    Comparator<LabWork> compareByMinPoint = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return o1.getMinimalPoint() - o2.getMinimalPoint();
        }
    };

    //то же, но если в первом случае было сравнение по возрастанию, то здесь по убыванию
    Comparator<LabWork> compareByMinPointReverse = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return o2.getMinimalPoint() - o1.getMinimalPoint();
        }
    };

    //компаратор для сравнения элементов по ID
    Comparator<LabWork> compareByID = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return (int) (o1.getId()-o2.getId());
        }
    };

    //геттер и сеттер, но их используем только с экспериментальных целях
    public void setLabWorkSet(HashSet<LabWork> labWorkSet) {
        this.labWorkSet = labWorkSet;
    }

    public Set<LabWork> getLabWorkSet() {
        return labWorkSet;
    }
    //to String. Пусть будет.
    @Override
    public String toString() {
        return "Root{" +
                ", labs=" + this.labWorkSet +
                '}';
    }

    //Отсюда начинаются все методы. Они же и есть ресиверы нашей программы.
    //Метод show: показывает элементы коллекции
    public void show() {
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(labWorkSet);

        labWorkList.sort(compareByID);
        for (LabWork lab : labWorkList) {
            System.out.println(lab);
        }
    }

    //доп. метод для команды info (ниже)
    private LocalDate getCreationDate(){
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(labWorkSet);
        LabWork minimum = Collections.min(labWorkSet,compareByID);

        return minimum.getCreationDate().toLocalDate();
    }

    //Метод info: получение информации о коллекции
    public void getInfo(){
        System.out.println("Тип коллекции: "+labWorkSet.getClass());
        System.out.println("Дата инициализации: "+getCreationDate());
        System.out.println("Количество элементов: "+labWorkSet.size());
    }

    //Добавить элемент в коллекцию
    public void addElement(String name) throws IOException, ParseException {
        Coordinates coordinates = addCoordinates();
        Person author = addPerson();
        int minimalPoint = addMinimalPoint();
        int tunedInWorks = addTunedInWorks();
        Difficulty difficulty = addDifficulty();
        LabWork e = new LabWork(name,minimalPoint,tunedInWorks,difficulty,coordinates,author);
        labWorkSet.add(e);
    }

    //Добавить элемент в коллекцию, если он больше остальных. Сравниваю по minimalPoint
    public void addIfMax(String name) throws IOException, ParseException {
        Coordinates coordinates = addCoordinates();
        Person author = addPerson();
        int minimalPoint = addMinimalPoint();
        int tunedInWorks = addTunedInWorks();
        Difficulty difficulty = addDifficulty();
        LabWork e = new LabWork(name,minimalPoint,tunedInWorks,difficulty,coordinates,author);
        LabWork maximum = Collections.max(labWorkSet,compareByMinPoint);
        if ((e.getMinimalPoint() - maximum.getMinimalPoint()) > 0){
            labWorkSet.add(e);
        }
    }

    //Доп метод для add: добавить minimalPoint
    private static int addMinimalPoint() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите minimalPoint:");
        int minimalPoint = Integer.parseInt(reader.readLine());
        return minimalPoint;
    }

    //Доп метод для add: добавить tunedInWorks
    private static int addTunedInWorks() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите tuned in works:");
        int tunedInWorks = Integer.parseInt(reader.readLine());
        return tunedInWorks;
    }

    //Доп метод для add: добавить координаты
    private static Coordinates addCoordinates() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите координату x:");
        int x = Integer.parseInt(reader.readLine());
        System.out.println("Введите координату y:");
        double y = Double.parseDouble(reader.readLine());

        return new Coordinates(x, y);
    }

    //Доп метод для add: добавить сложность
    private static Difficulty addDifficulty() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сложность работы (VERY_EASY, EASY, VERY_HARD, IMPOSSIBLE, HOPELESS:");
        String difficulty = reader.readLine();
        return Difficulty.valueOf(difficulty);
    }

    //Доп метод для add: добавить имя. Пока не использован, но может понадобиться для доработки update
    private static String addName() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя:");
        String name = reader.readLine();
        return name;
    }

    //Доп метод для add: добавить автора
    private static Person addPerson() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя автора: ");
        String name = reader.readLine();
        System.out.println("Введите рост автора: ");
        float height = Float.parseFloat(reader.readLine());
        System.out.println("Введите дату рождения автора (гг-мм-дд): ");
        LocalDate birthday = LocalDate.parse(reader.readLine());
        String[] dateSplit = birthday.toString().split("-");
        String date = dateSplit[2]+"-"+dateSplit[1]+"-"+dateSplit[0];
        System.out.println("Введите цвет глаз автора (GREEN, RED, ORANGE, WHITE, BLACK): ");
        String color = reader.readLine();
        return new Person(name, Color.valueOf(color), height, date);
    }

    //Удалить элемент из коллекции
    public void removeEl(int id) {
        int flag = 0;
        for (LabWork lab : labWorkSet) {
            if (lab.getId() == id){
                labWorkSet.remove(lab);
                flag = 1;
                break;
            }
        }
        if (flag == 0){
            System.out.println("Элемент с данным id не найден!");
        }
    }

    //Очистить коллекцию
    public void clearCollection(){
        labWorkSet.clear();
    }

    //Вывести уникальные значения tunedInWorks
    public void printUniqueTunedInWorks(){
        Set<Integer> unique = new HashSet<>();
        for (LabWork lab: labWorkSet){
            unique.add(lab.getTunedInWorks());
        }

//        unique.forEach(System.out::println);

//        for (int el: unique){
//            System.out.print(el+" ");
//        }

        printCollection(unique);
        System.out.println("\n");
    }

    //Доп метод для вывода коллекции элементов (используется в команде выше)
    private void printCollection(Collection<Integer> collection) {
        collection.forEach(System.out::println);
    }

    //Вывести значения tunedInWorks в порядке возрастания
    public void printFieldAscendingTunedInWorks() {
        List<Integer> tunedInWorks = new LinkedList<>();
        for (LabWork lab: labWorkSet){
            tunedInWorks.add(lab.getTunedInWorks());
        }

        Collections.sort(tunedInWorks);

        printCollection(tunedInWorks);

//        for (int el: tunedInWorks){
//            System.out.print(el+" ");
//        }

        System.out.println("\n");
    }

    //Вывести максимального автора. Я хрен знает, как сравнить авторов, поэтому их сравнивают по имени.
    public void maxByAuthor() {
        List<Person> authors = new ArrayList<>();
        // вывести в метод
        for (LabWork lab: labWorkSet){
            authors.add(lab.getAuthor());
        }
        Comparator<Person> compareByName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
               return o1.getName().compareTo(o2.getName());
            }
        };
        Person greatest = Collections.max(authors,compareByName);
        System.out.println(" ----- Автор -----");
        System.out.println("Ымя: " + greatest.getName());
        System.out.println("Дата рождения: " + greatest.getBirthday());
        System.out.println("Рост: " + greatest.getHeight());
        System.out.println("Цвет глаз: " + greatest.getEyeColor());
    }

    //Удалить из коллекции все элементы, превышающие заданный
    public void removeGreater(String e){
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(labWorkSet);
        labWorkList.sort(compareByMinPointReverse);

        for (LabWork el:labWorkList) {
            if(el.getName().equals(e)){break;}
            labWorkSet.remove(el);
        }
    }

    //Удалить из коллекции все элементы, меньшие, чем заданный
    public void removeLower(String e){
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(labWorkSet);
        labWorkList.sort(compareByMinPoint);

        for (LabWork el:labWorkList) {
            if(el.getName().equals(e)){break;}
            labWorkSet.remove(el);
        }
    }

    //Обновить элемент. Возможно, буду дорабатывать этот метод
    public void update(int id, String name) throws IOException, ParseException {
        boolean flag = true;

        for (LabWork lab: labWorkSet) {
            if (lab.getId() == id) {
                Coordinates coordinates = addCoordinates();
                Person author = addPerson();
                int minimalPoint = addMinimalPoint();
                int tunedInWorks = addTunedInWorks();
                Difficulty difficulty = addDifficulty();
                LabWork e = new LabWork(name,minimalPoint,tunedInWorks,difficulty,coordinates,author);

                lab.setName(e.getName());
                lab.setAuthor(e.getAuthor());
                lab.setCoordinates(e.getCoordinates());
                lab.setDifficulty(e.getDifficulty());
                lab.setMinimalPoint(e.getMinimalPoint());
                lab.setTunedInWorks(e.getTunedInWorks());

                flag = false;
                break;
            }
        }
        if (flag){
            System.out.println("Элемент с данным ID отсутствует!");
        }
    }

    //я устану объяснять все детали паттерна Command в каждом из классов, поэтому просмотри ты только классы Invoker, UpdateElementCommand и Update
}
