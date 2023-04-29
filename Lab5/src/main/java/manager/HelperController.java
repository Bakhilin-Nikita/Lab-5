package manager;

import command.commands.noInputCommands.help.Help;
import exceptions.InvalidFieldY;
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
import java.net.SocketException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

/**
 *
 */

public class HelperController {
    private Root root; // Не может быть null
    private ArrayList<String> paths = new ArrayList<>(); // Не может быть null
    private BufferedReader reader; // Не может быть null

    private Server server;

    private  String fileName;

    public HelperController() throws IOException {}

    /**
     * Конструктор создает объект который выгружает данные из файла в нашу переменную.
     * Устанавливает директорию корневую.
     */
    public HelperController(String file, Root root, Server server) throws IOException {
        this.fileName = file;
        ParserFromJson parserFromJson = new ParserFromJson();
        this.root = root;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.paths.add(System.getProperty("user.dir"));
        this.server = server;
    }

    /**
     * Метод позволяет добавить в коллекцию путь которого еще не было в коллекции
     * Относится к методу execute_script.
     * Если пути еще не было вернет true, иначе false
     *
     * @param pathToFile
     * @return
     */
    public boolean addToPaths(String pathToFile) {
        pathToFile = System.getProperty("user.dir") + "/" + pathToFile;
        for (int i = 0; i < getPaths().size(); i++) {
            if (Objects.equals(getPaths().get(i).trim(), pathToFile)) {
                return false;
            }
        }

        getPaths().add(pathToFile);

        return true;
    }


    /**
     * Метод обновляет объект который находится в коллекции, по его id
     * При это не изменяя его id.
     * Цикл for прогоняется по коллекции, если id найдено, то меняем поля.
     *
     * @param id
     * @throws IOException
     * @throws ParseException
     */
    public void update(int id) throws IOException, ParseException {
        boolean flag = true;
        for (LabWork lab : getRoot().getLabWorkSet()) {
            if (lab.getId() == id) {
                LabWork e = adder();

                lab.setName(e.getName());
                lab.setAuthor(e.getAuthor());
                lab.setCoordinates(e.getCoordinates());
                lab.setDifficulty(e.getDifficulty());
                lab.setMinimalPoint(e.getMinimalPoint());
                lab.setTunedInWorks(e.getTunedInWorks());
                System.out.println("Элемент успешно добавлен в коллекцию!");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("Элемент с данным ID отсутствует!");
        }
    }

    private LabWork adder() throws IOException {
        getServer().sentToClient("Введите название Лабораторной работы: ");
        String name = null;
        while (name == null) {
            try {
                name = getServer().dataFromClient().trim();
                if(name == null || name.isEmpty()){
                    throw new RuntimeException("Пустая строка не может именем лабораторной работы. Попробуй ещё раз.");
                }
            } catch (RuntimeException e) {
                getServer().sentToClient(e.getMessage());
                name = null;
            }
        }
        Coordinates coordinates = addCoordinates();
        Person author = addPerson();
        int minimalPoint = addMinimalPoint();
        Long tunedInWorks = addTunedInWorks();
        Difficulty difficulty = addDifficulty();
        LabWork e = new LabWork(generateId(), name, minimalPoint, tunedInWorks, difficulty, coordinates, author);
        return e;
    }


    /**
     * Метод показывает все элементы коллекции.
     * Сортируя их по id
     */
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

    /**
     * Дополнительный метод для {@link #getInfo()}
     *
     * @return
     */
    private LocalDate getCreationDate() {
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(getRoot().getLabWorkSet());
        LabWork minimum = Collections.min(labWorkList, compareByID);
        return minimum.getCreationDate().toLocalDate();
    }

    /**
     * Метод info: получение информации о коллекции
     */
    public void getInfo() throws IOException {
        if (getRoot().getLabWorkSet().isEmpty()) {
            getServer().sentToClient("Информация по коллекции не найдена! Возможно она удаленна.");
        } else {
            getServer().sentToClient("Тип коллекции: " + getRoot().getLabWorkSet().getClass().getSimpleName() +
                    "Дата инициализации: " + getCreationDate() +
                    "Количество элементов: " + getRoot().getLabWorkSet().size()
            );
        }
    }

    public void getHelp() throws IOException {
        getServer().sentToClient("Доступные  команды\n" +
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "max_by_author : вывести любой объект из коллекции, значение поля author которого является максимальным\n" +
                "print_unique_tuned_in_works : вывести уникальные значения поля tunedInWorks всех элементов в коллекции\n" +
                "print_field_ascending_tuned_in_works : вывести значения поля tunedInWorks всех элементов в порядке возрастания");
    }

    /**
     * Метод удаляет из коллекции все элементы, превышающие заданный.
     *
     * @param e
     */
    /**
     * Метод удаляет из коллекции все элементы, превышающие заданный.
     *
     */
    public void removeGreater() throws IOException, ParseException {
        System.out.println("Введите элемент для сравнения:");
        LabWork comparableEl = adder();
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.add(comparableEl);
        labWorkList.addAll(getRoot().getLabWorkSet());
        labWorkList.sort(compareByDifficultyReverse);
        labWorkList.sort(compareByMinPointReverse);
        labWorkList.sort(compareByTunedInWorksReverse);

        for (LabWork el : labWorkList) {
            if (el.equals(comparableEl)) {
                break;
            }
            getRoot().getLabWorkSet().remove(el);
        }

        System.out.println("Все элементы, большие данного, были удалены.");
    }

    /**
     * Метод удаляет все элементы меньшие чем заданный.
     *
     */
    public void removeLower() throws IOException {
        System.out.println("Введите элемент для сравнения:");
        LabWork comparableEl = adder();
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.add(comparableEl);
        labWorkList.addAll(getRoot().getLabWorkSet());
        labWorkList.sort(compareByDifficulty);
        labWorkList.sort(compareByMinPoint);
        labWorkList.sort(compareByTunedInWorks);

        for (LabWork el : labWorkList) {
            if (el.equals(comparableEl)) {
                break;
            }
            getRoot().getLabWorkSet().remove(el);
        }

        System.out.println("Все элементы меньше данного были удалены.");
    }

    /**
     * Метод удаляет элемент коллекции по id.
     */
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

    /**
     * Метод добавляет элемент в коллекцию
     *
     * @throws IOException
     * @throws ParseException
     * @see #addCoordinates()
     * @see #addCoordinates()
     * @see #addMinimalPoint()
     * @see #addTunedInWorks()
     * @see #addPerson()
     */
    public void addElement() throws IOException, ParseException {
        LabWork lab = adder();

        if (getRoot().getLabWorkSet().add(lab))
            getServer().sentToClient("Элемент успешно добавлен в коллекцию!");
        else
            getServer().sentToClient("К сожалению, что-то пошло не так. Попробуйте еще раз!");
    }

    /**
     * Метод генерирует id нового объекта
     *
     * @return
     */
    public int generateId() {
        Map<Integer, LabWork> labs = new HashMap<>();
        for (LabWork lab : getRoot().getLabWorkSet())
            labs.put((int) lab.getId(), lab);
        labs = sortByKeys(labs);
        Integer size = labs.size();
        for (Map.Entry<Integer, LabWork> entry : labs.entrySet()){
            if(size.equals(entry.getKey())){
                size += 1;
            }
        }
        if (labs.size() == 0)
            return 0;
        return size;
    }

    /**
     * Сортирует коллекцию объектов по ключу.
     *
     * @param unsortedMap
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> sortByKeys(Map<K, V> unsortedMap) {
        return new TreeMap<>(unsortedMap);
    }


    /**
     * Добавить элемент в коллекцию, если он больше остальных. Сравнивая по
     *
     * @throws IOException
     * @throws ParseException
     */
    public void addIfMax() throws IOException, ParseException {
        LabWork e = adder();
        LabWork maximum = Collections.max(getRoot().getLabWorkSet(), compareByMinPoint);
        if ((e.getMinimalPoint() - maximum.getMinimalPoint()) > 0) {
            getRoot().getLabWorkSet().add(e);
            System.out.println("Элемент добавлен в коллекцию");
        }
    }

    /**
     * Метод обрабатывает поле
     * @return
     */
    private Long addTunedInWorks() throws IOException {
        Long tunedInWorks = null;
        boolean flag = false;
        getServer().sentToClient("Введите tunedInWorks(1-1000):");
        String commandValue = getServer().dataFromClient().trim();
        if (!commandValue.trim().isEmpty())
            while (!flag) {
                    try {
                        if (commandValue != null) {
                            String num = commandValue;
                            commandValue = null;
                            tunedInWorks = Long.parseLong(num);
                        } else {
                            getServer().sentToClient("Введите tunedInWorks(1-1000):");
                            tunedInWorks = checkOnLong();
                        }
                        if (tunedInWorks > 0 && tunedInWorks < 1001) {
                            flag = true;
                        }
                        //commandValue = null;
                    } catch (NumberFormatException e) {
                        getServer().sentToClient(e.getMessage().trim());
                    }
                }


        return tunedInWorks;
    }

    /**
     * Метод обрабатывает поле {@link LabWork#getMinimalPoint()}
     * Дополнительный метод для {@link #addElement()}
     *
     * @return
     */
    private int addMinimalPoint() throws IOException {
        int minimalPoint = 0;
        boolean flag = false;
        while (!flag) {
            getServer().sentToClient("Введите minimalPoint(1-1000):");
            minimalPoint = checkOnInt();
            if (minimalPoint > 0 && minimalPoint < 1001)
                flag = true;
            else
                getServer().sentToClient("Вы ввели неккоректное число! Число не может быть отрицательным, или равно нулю.");
        }

        return minimalPoint;
    }

    /**
     * Метод обрабатывает поле {@link LabWork#coordinates}
     * Дополнительный метод для {@link #addElement(String)}
     *
     * @return
     * @throws IOException
     */
    private Coordinates addCoordinates() throws IOException {
        boolean flag = false;
        getServer().sentToClient("Введите координату x: ");
        int x = checkOnInt();
        double y = 0;
        while(!flag) {
            try {
                getServer().sentToClient("Введите координату y: ");
                y = checkOnDouble();

                if (y < -184) {
                    throw new InvalidFieldY("Field Y must be > -184 and can not be NULL");
                }
                flag = true;
            } catch (InvalidFieldY e) {
                getServer().sentToClient(e.getMessage());
            }
        }

        return new Coordinates(x, y);
    }

    /**
     * Метод сохраняет коллекцию в файл.
     */
    public void save() throws IOException {
        ParserToJson parserToJson = new ParserToJson();

        if (parserToJson.serialization(getRoot().getLabWorkSet(), this.fileName))
            getServer().sentToClient("Коллекция " + getRoot().getLabWorkSet().getClass().getSimpleName() + " успешно сохранена в файл!");
        else
            getServer().sentToClient("Что-то пошлое не так :(");
    }

    /**
     * Доп метод для {@link #addElement(String)}: добавить сложность
     *
     * @return
     * @throws IOException
     */
    private Difficulty addDifficulty() throws IOException {
        getServer().sentToClient("Введите сложность работы (VERY_EASY, EASY, VERY_HARD, IMPOSSIBLE, HOPELESS:");
        String difficulty = checkOnEnum(Difficulty.class);
        return Difficulty.valueOf(difficulty);
    }


    /**
     * Доп метод для {@link #addElement(String)}: добавить автора
     *
     * @return
     * @throws IOException
     */
    private Person addPerson() throws IOException {
        boolean flag = false;
        String name = null;
        while(!flag) {
            getServer().sentToClient("Введите имя автора: ");
            name = getServer().dataFromClient().trim();
            if (!name.isEmpty())
                flag = true;
            else
                getServer().sentToClient("Поле имя автора не может быть пустым");
        }

        flag = false;
        float height = 0;
        while (!flag) {
            getServer().sentToClient("Введите рост автора: ");
            Float h = checkOnFloat();
            try {
                if (h.isInfinite())
                    throw new IllegalArgumentException("Некорректный ввод. Повторите попытку.");
                if ( h < 272 && h > 0 ) {
                    flag = true;
                    height = h;
                } else {
                    getServer().sentToClient("Вы ввели неправильный рост! Доступно в интервале от 0 до 272.");
                }
            } catch (IllegalArgumentException e) {
                getServer().sentToClient(e.getMessage());
            }
        }


        String date = null;
        LocalDate birthday = null;
        while (date == null) {
            try {
                getServer().sentToClient("Введите дату рождения автора (гггг-мм-дд): ");
                birthday = LocalDate.parse(getServer().dataFromClient());
                String[] dateSplit = birthday.toString().split("-");
                if (Integer.parseInt(dateSplit[0]) >= 1907 && Integer.parseInt(dateSplit[0]) < 2015)
                    date = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
                else
                    getServer().sentToClient("Ты не мог родиться в такой год. Самый старый человек родился в 1907 году.Мария Браньяс Морера");
            } catch (DateTimeException e) {
                getServer().sentToClient(e.getMessage());
            }
        }

        getServer().sentToClient("Введите цвет глаз автора (GREEN, RED, ORANGE, WHITE, BLACK): ");

        String color = checkOnEnum(Color.class);

        return new Person(name, Color.valueOf(color), height, date);
    }

    /**
     * Метод проверяет является ли число типом {@link Double}
     *
     * @return
     */
    private double checkOnDouble() {
        double y = 0;
        boolean flag = false;
        while (!flag) {
            try {
                y = Double.parseDouble(getServer().dataFromClient().trim());
                flag = true;
            } catch (NumberFormatException e) {
                flag = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return y;
    }

    private Long checkOnLong() {
        long y = 0;
        boolean flag = false;
        while (!flag)
            try {
                y = Long.parseLong(getServer().dataFromClient().trim());
                flag = true;
            } catch (NumberFormatException e) {
                flag = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return y;
    }

    /**
     * Метод проверяет является ли число типом {@link Integer}
     *
     * @return
     */
    private int checkOnInt() {
        int y = 0;
        boolean flag = false;
        while (!flag)
            try {
                y = Integer.parseInt(getServer().dataFromClient());
                flag = true;
            } catch (NumberFormatException | IOException e) {
                flag = false;
            }
        return y;
    }

    /**
     * Метод проверяет является ли число типом {@link Enum}
     *
     * @return
     */
    private String checkOnEnum(Class className) {
        boolean flag = false;
        String enumValue = null;
        while (!flag) {
            try {
                enumValue = getServer().dataFromClient().toUpperCase().trim();
                Enum.valueOf(className, enumValue);
                flag = true;
            } catch (IllegalArgumentException e) {
                flag = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return enumValue;
    }

    /**
     * Метод проверяет является ли число типом {@link Float}
     *
     * @return
     */
    private float checkOnFloat() {
        float y = 0;
        boolean flag = false;
        while (!flag)
            try {
                String cmd = getServer().dataFromClient().trim();
                if (cmd != null) {
                    y = Float.parseFloat(cmd);
                    flag = true;
                }
            } catch (NumberFormatException e) {
                flag = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return y;
    }

    /**
     * Метод производит очистку коллекции.
     */
    public void clearCollection() {
        getRoot().getLabWorkSet().clear();
        if (getRoot().getLabWorkSet().isEmpty())
            System.out.println("Коллекция " + getRoot().getLabWorkSet().getClass().getSimpleName() + " очищена!");
    }

    /**
     * Сравнение авторов по имени, вывод максимального.
     */
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

    /**
     * Вывести уникальные значения tunedInWorks
     */
    public void printUniqueTunedInWorks() {
        Set<Integer> unique = new HashSet<>();
        for (LabWork lab : getRoot().getLabWorkSet()) {
            unique.add(lab.getTunedInWorks());
        }

        printCollection(unique);
        System.out.println("\n");
    }

    /**
     * Доп метод для вывода коллекции элементов в {@link #printFieldAscendingTunedInWorks()}
     *
     * @param collection
     */
    private void printCollection(Collection<Integer> collection) {
        collection.forEach(System.out::println);
    }

    /**
     * Вывести значения {@link LabWork#tunedInWorks} в порядке возрастания
     */
    public void printFieldAscendingTunedInWorks() {
        List<Integer> tunedInWorks = new LinkedList<>();
        for (LabWork lab : getRoot().getLabWorkSet()) {
            tunedInWorks.add(lab.getTunedInWorks());
        }

        Collections.sort(tunedInWorks);

        printCollection(tunedInWorks);


        System.out.println("\n");
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
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

    Comparator<LabWork> compareByTunedInWorks = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            if (o2.getTunedInWorks() == null && o1.getTunedInWorks() == null)
                return 0;
            else if (o2.getTunedInWorks() == null && o1.getTunedInWorks() != null)
                return 1;
            else if (o2.getTunedInWorks() != null && o1.getTunedInWorks() == null) {
                return -1;
            }
            return o1.getTunedInWorks() - o2.getTunedInWorks();
        }
    };

    Comparator<LabWork> compareByTunedInWorksReverse = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            if (o1.getTunedInWorks() == null && o2.getTunedInWorks() == null)
                return 0;
            else if (o1.getTunedInWorks() == null && o2.getTunedInWorks() != null)
                return 1;
            else if (o1.getTunedInWorks() != null && o2.getTunedInWorks() == null) {
                return -1;
            }
            return o2.getTunedInWorks() - o1.getTunedInWorks();
        }
    };

    Comparator<LabWork> compareByDifficulty = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return o1.getDifficulty().getLevel() - o2.getDifficulty().getLevel();
        }
    };

    Comparator<LabWork> compareByDifficultyReverse = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return o2.getDifficulty().getLevel() - o1.getDifficulty().getLevel();
        }
    };

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    public Root getRoot() {
        return root;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public String toString() {
        return "HelperController{" +
                "root=" + root +
                ", paths=" + paths +
                ", reader=" + reader +
                ", compareByName=" + compareByName +
                ", compareByMinPoint=" + compareByMinPoint +
                ", compareByMinPointReverse=" + compareByMinPointReverse +
                ", compareByID=" + compareByID +
                '}';
    }
}
