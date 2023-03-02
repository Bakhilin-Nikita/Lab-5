package org.example.lab5.parserFromJson;

import org.example.lab5.LabWork;
import org.example.lab5.entity.Coordinates;
import org.example.lab5.entity.Person;
import org.example.lab5.entity.enums.Color;
import org.example.lab5.entity.enums.Difficulty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Root {

    private Set<LabWork> labWorkSet;

    public Root() {
        this.labWorkSet = new HashSet<>();
    }

    Comparator<LabWork> compareByMinPoint = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return o1.getMinimalPoint() - o2.getMinimalPoint();
        }
    };

    Comparator<LabWork> compareByMinPointReverse = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return o2.getMinimalPoint() - o1.getMinimalPoint();
        }
    };

    Comparator<LabWork> compareByID = new Comparator<LabWork>() {
        @Override
        public int compare(LabWork o1, LabWork o2) {
            return (int) (o1.getId()-o2.getId());
        }
    };

    public void setLabWorkSet(HashSet<LabWork> labWorkSet) {
        this.labWorkSet = labWorkSet;
    }

    public Set<LabWork> getLabWorkSet() {
        return labWorkSet;
    }

    @Override
    public String toString() {
        return "Root{" +
                ", labs=" + this.labWorkSet +
                '}';
    }

    public void show() {
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(labWorkSet);

        labWorkList.sort(compareByID);
        for (LabWork lab : labWorkList) {
            System.out.println(lab);
        }
    }

    public void addElement(String name) throws IOException {
        Coordinates coordinates = addCoordinates();
        Person author = addPerson();
        int minimalPoint = addMinimalPoint();
        int tunedInWorks = addTunedInWorks();
        Difficulty difficulty = addDifficulty();
        LabWork e = new LabWork(name,minimalPoint,tunedInWorks,difficulty,coordinates,author);
        labWorkSet.add(e);
    }

    public void addIfMax(String name) throws IOException{
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

    private static int addMinimalPoint() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите minimalPoint:");
        int minimalPoint = Integer.parseInt(reader.readLine());
        return minimalPoint;
    }

    private static int addTunedInWorks() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите tuned in works:");
        int tunedInWorks = Integer.parseInt(reader.readLine());
        return tunedInWorks;
    }

    private static Coordinates addCoordinates() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите координату x:");
        int x = Integer.parseInt(reader.readLine());
        System.out.println("Введите координату y:");
        double y = Double.parseDouble(reader.readLine());

        return new Coordinates(x, y);
    }

    private static Difficulty addDifficulty() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сложность работы (VERY_EASY, EASY, VERY_HARD, IMPOSSIBLE, HOPELESS:");
        String difficulty = reader.readLine();
        return Difficulty.valueOf(difficulty);
    }

    private static Person addPerson() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя автора: ");
        String name = reader.readLine();
        System.out.println("Введите рост автора: ");
        float height = Float.parseFloat(reader.readLine());
        System.out.println("Введите дату рождения автора: ");
        String birthday = reader.readLine();
        System.out.println("Введите цвет глаз автора (GREEN, RED, ORANGE, WHITE, BLACK): ");
        String color = reader.readLine();
        return new Person(name, Color.valueOf(color), height, birthday);
    }

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

    public void clearCollection(){
        labWorkSet.clear();
    }

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

    private void printCollection(Collection<Integer> collection) {
        collection.forEach(System.out::println);
    }

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

    public void addIfMax(LabWork e) {
        LabWork maximum = Collections.max(labWorkSet,compareByMinPoint);
        if ((e.getMinimalPoint() - maximum.getMinimalPoint()) > 0){
            labWorkSet.add(e);
        }
    }

    public void removeGreater(String e){
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(labWorkSet);
        labWorkList.sort(compareByMinPointReverse);

        for (LabWork el:labWorkList) {
            if(el.getName().equals(e)){break;}
            labWorkSet.remove(el);
        }
    }

    public void removeLower(String e){
        List<LabWork> labWorkList = new ArrayList<>();
        labWorkList.addAll(labWorkSet);
        labWorkList.sort(compareByMinPoint);

        for (LabWork el:labWorkList) {
            if(el.getName().equals(e)){break;}
            labWorkSet.remove(el);
        }
    }

    public void update(int id, LabWork e) {
        boolean flag = true;
        for (LabWork lab: labWorkSet) {
            if (lab.getId() == id) {
                lab.setName(e.getName());
                lab.setAuthor(e.getAuthor());
                lab.setCoordinates(e.getCoordinates());
                lab.setDifficulty(e.getDifficulty());
                lab.setMinimalPoint(e.getMinimalPoint());
                lab.setTunedInWorks(e.getTunedInWorks());
                flag = false;
                break;
            }
            if (flag) {
                System.out.println(" Элемент с данным ID отсутствует ");
            }
        }
    }
}
