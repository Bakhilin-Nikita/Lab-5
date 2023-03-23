package command;

import object.Coordinates;
import object.LabWork;
import object.Person;
import object.enums.Color;
import object.enums.Difficulty;
import parser.Root;
import parser.parserFromJson.ParserFromJson;
import parser.parserToJson.ParserToJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class HelperController {
    private ParserFromJson parser = new ParserFromJson();
    private Root root;

    private ArrayList<String> paths = new ArrayList<>();

    private BufferedReader reader;

    public HelperController() throws IOException {
        ParserFromJson parserFromJson = new ParserFromJson();
        this.root = parserFromJson.parse();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.paths.add("/home/studs/s367870/");
    }

    public boolean addToPaths(String pathToFile) {
        int j = 0;
        pathToFile = "home/studs/s367870/"+pathToFile;
        for (int i = 0; i < getPaths().size(); i++) {
            if (Objects.equals(getPaths().get(i).trim(), pathToFile)) {
                return false;
            }
        }

        getPaths().add(pathToFile);

        return true;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    public Root getRoot() {
        return root;
    }


    //Обновить элемент. Возможно, буду дорабатывать этот метод
    public void update(int id) throws IOException, ParseException {
        boolean flag = true;


        for (LabWork lab : getRoot().getLabWorkSet()) {
            if (lab.getId() == id) {
                System.out.println("Введите название Лабараторной работы: ");
                String name = reader.readLine();
                Coordinates coordinates = addCoordinates();
                Person author = addPerson();
                int minimalPoint = addMinimalPoint();
                int tunedInWorks = addTunedInWorks();
                Difficulty difficulty = addDifficulty();
                LabWork e = new LabWork(name, minimalPoint, tunedInWorks, difficulty, coordinates, author);


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
        if (flag) {
            System.out.println("Элемент с данным ID отсутствует!");
        }

        for (LabWork lab : getRoot().getLabWorkSet())
            System.out.println("Author name: " + lab.getAuthor().getName());
    }


    public void show() {
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(getRoot().getLabWorkSet());

        if (labWorkList.isEmpty()) {
            System.out.println("Коллекция пустая");
        } else {
            labWorkList.sort(compareByID);
            for (LabWork lab : labWorkList) {
                System.out.println(lab);
            }
        }

    }

    //доп. метод для команды info (ниже)
    private LocalDate getCreationDate() {
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(getRoot().getLabWorkSet());
        LabWork minimum = Collections.min(labWorkList, compareByID);
        return minimum.getCreationDate().toLocalDate();
    }

    //Метод info: получение информации о коллекции
    public void getInfo() {
        if (getRoot().getLabWorkSet().isEmpty()) {
            System.out.println("Информация по коллекции не найдена! Возможно она удаленна.");
        } else {
            System.out.println("Тип коллекции: " + getRoot().getLabWorkSet().getClass().getSimpleName());
            System.out.println("Дата инициализации: " + getCreationDate());
            System.out.println("Количество элементов: " + getRoot().getLabWorkSet().size());
        }
    }

    //Удалить из коллекции все элементы, превышающие заданный
    public void removeGreater(String e) {
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(getRoot().getLabWorkSet());
        labWorkList.sort(compareByMinPointReverse);

        for (LabWork el : labWorkList) {
            if (el.getName().equals(e)) {
                break;
            }
            getRoot().getLabWorkSet().remove(el);
        }

        System.out.println("Все элементы выше данного, были удаленны.");
    }

    //Удалить из коллекции все элементы, меньшие, чем заданный
    public void removeLower(String e) {
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(getRoot().getLabWorkSet());
        labWorkList.sort(compareByName);

        for (LabWork el : labWorkList) {
            if (el.getName().equals(e)) {
                break;
            }
            getRoot().getLabWorkSet().remove(el);
        }

        System.out.println("Все элементы меньше данного были удалены.");
    }

    //Удалить элемент из коллекции
    public void removeEl(int id) {
        int flag = 0;
        for (LabWork lab : getRoot().getLabWorkSet()) {
            if (lab.getId() == id) {
                getRoot().getLabWorkSet().remove(lab);
                flag = 1;
                System.out.println("Элемент с данными id удалён.");
                break;
            }
        }
        if (flag == 0) {
            System.out.println("Элемент с данным id не найден!");
        }
    }

    //Добавить элемент в коллекцию
    public void addElement() throws IOException, ParseException {
        System.out.println("Введите название Лабараторной работы: ");
        String name = reader.readLine();
        Coordinates coordinates = addCoordinates();
        Person author = addPerson();
        int minimalPoint = addMinimalPoint();
        int tunedInWorks = addTunedInWorks();
        Difficulty difficulty = addDifficulty();
        int id = generateId();
        LabWork e = new LabWork(id, name, minimalPoint, tunedInWorks, difficulty, coordinates, author);

        if (getRoot().getLabWorkSet().add(e))
            System.out.println("Элемент успешно добавлен в коллекцию!");
        else
            System.out.println("К сожалению, что-то пошло не так. Попробуйте еще раз!");
    }

    public int generateId() {
        Map<Integer, LabWork> labs = new HashMap<>();
        for (LabWork lab : getRoot().getLabWorkSet())
            labs.put((int) lab.getId(), lab);
        labs = sortByKeys(labs);
        return labs.size() + 1;
    }

    public <K, V> Map<K, V> sortByKeys(Map<K, V> unsortedMap) {
        return new TreeMap<>(unsortedMap);
    }

    private int addMinimalPoint() throws IOException {
        System.out.println("Введите minimalPoint:");
        int minimalPoint = checkOnInt();
        return minimalPoint;
    }

    //Добавить элемент в коллекцию, если он больше остальных. Сравниваю по minimalPoint
    public void addIfMax(String name) throws IOException, ParseException {
        Coordinates coordinates = addCoordinates();
        Person author = addPerson();
        int minimalPoint = addMinimalPoint();
        int tunedInWorks = addTunedInWorks();
        Difficulty difficulty = addDifficulty();
        LabWork e = new LabWork(name, minimalPoint, tunedInWorks, difficulty, coordinates, author);
        LabWork maximum = Collections.max(getRoot().getLabWorkSet(), compareByMinPoint);
        if ((e.getMinimalPoint() - maximum.getMinimalPoint()) > 0) {
            getRoot().getLabWorkSet().add(e);
        }
    }

    //Доп метод для add: добавить tunedInWorks
    private int addTunedInWorks() {
        System.out.println("Введите tuned in works:");
        int tunedInWorks = checkOnInt();
        return tunedInWorks;
    }

    //Доп метод для add: добавить координаты
    private Coordinates addCoordinates() throws IOException {
        System.out.println("Введите координату x:");
        int x = checkOnInt();
        System.out.println("Введите координату y:");
        double y = checkOnDouble();

        return new Coordinates(x, y);
    }

    private double checkOnDouble() {
        double y = 0;
        boolean flag = false;
        while (!flag) {
            try {
                y = Double.parseDouble(reader.readLine());
                flag = true;
            } catch (IOException | NumberFormatException e) {
                flag = false;
            }
        }

        return y;
    }

    private int checkOnInt() {
        int y = 0;
        boolean flag = false;
        while (!flag)
            try {
                y = Integer.parseInt(reader.readLine());
                flag = true;
            } catch (NumberFormatException | IOException e) {
                flag = false;
            }
        return y;
    }

    public void save() {
        ParserToJson parserToJson = new ParserToJson();

        if (parserToJson.serialization(getRoot().getLabWorkSet()))
            System.out.println("Коллекция " + getRoot().getLabWorkSet().getClass().getSimpleName() + " успешно сохранена в файл!");
        else
            System.out.println("Что-то пошлое не так :(");
    }

    //Доп метод для add: добавить сложность
    private Difficulty addDifficulty() throws IOException {
        System.out.println("Введите сложность работы (VERY_EASY, EASY, VERY_HARD, IMPOSSIBLE, HOPELESS:");
        String difficulty = checkOnEnum(Difficulty.class);
        return Difficulty.valueOf(difficulty);
    }


    //Доп метод для add: добавить автора
    private Person addPerson() throws IOException {
        System.out.println("Введите имя автора: ");
        String name = reader.readLine();
        System.out.println("Введите рост автора: ");
        float height = checkOnFloat();
        String date = null;
        LocalDate birthday = null;
        while (birthday == null) {
            try {
                System.out.println("Введите дату рождения автора (гггг-мм-дд): ");
                birthday = LocalDate.parse(reader.readLine());
                String[] dateSplit = birthday.toString().split("-");
                date = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Введите цвет глаз автора (GREEN, RED, ORANGE, WHITE, BLACK): ");
        String color = checkOnEnum(Color.class);

        return new Person(name, Color.valueOf(color), height, date);
    }

    private String checkOnEnum(Class className) {
        boolean flag = false;
        String enumValue = null;
        while (!flag) {
            try {
                enumValue = reader.readLine().toUpperCase();
                Enum.valueOf(className, enumValue);
                flag = true;
            } catch (IllegalArgumentException | IOException e) {
                flag = false;
            }
        }

        return enumValue;
    }

    private float checkOnFloat() {
        float y = 0;
        boolean flag = false;
        while (!flag)
            try {
                String cmd = reader.readLine();
                if (cmd != null) {
                    y = Float.parseFloat(cmd);
                    flag = true;
                }
            } catch (NumberFormatException | IOException e) {
                flag = false;
            }
        return y;
    }

    //Очистить коллекцию
    public void clearCollection() {
        getRoot().getLabWorkSet().clear();
        if (getRoot().getLabWorkSet().isEmpty())
            System.out.println("Коллекция " + getRoot().getLabWorkSet().getClass().getSimpleName() + " очищена!");
    }

    //Вывести максимального автора. Я хрен знает, как сравнить авторов, поэтому их сравнивают по имени.
    public void maxByAuthor() {
        List<Person> authors = new ArrayList<>();

        if (!getRoot().getLabWorkSet().isEmpty()) {
            // вывести в метод
            for (LabWork lab : getRoot().getLabWorkSet()) {
                authors.add(lab.getAuthor());
            }
            Comparator<Person> compareByName = new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            };
            Person greatest = Collections.max(authors, compareByName);
            System.out.println(" ----- Автор -----");
            System.out.println("Uмя: " + greatest.getName());
            System.out.println("Дата рождения: " + greatest.getBirthday());
            System.out.println("Рост: " + greatest.getHeight());
            System.out.println("Цвет глаз: " + greatest.getEyeColor());
        }
    }

    //Вывести уникальные значения tunedInWorks
    public void printUniqueTunedInWorks() {
        Set<Integer> unique = new HashSet<>();
        for (LabWork lab : getRoot().getLabWorkSet()) {
            unique.add(lab.getTunedInWorks());
        }

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
        for (LabWork lab : getRoot().getLabWorkSet()) {
            tunedInWorks.add(lab.getTunedInWorks());
        }

        Collections.sort(tunedInWorks);

        printCollection(tunedInWorks);


        System.out.println("\n");
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public BufferedReader getReader() {
        return reader;
    }

    Comparator<LabWork> compareByName = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return o1.getName().length() - o2.getName().length();
        }
    };

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
            return (int) (o1.getId() - o2.getId());
        }
    };


}
