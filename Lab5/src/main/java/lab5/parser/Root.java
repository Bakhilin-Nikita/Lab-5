package lab5.parser;

import lab5.features.LabWork;
import lab5.features.Person;

import java.util.*;

public class Root {
    private HashSet<LabWork> labs;

    public Root(){}

    public Root(HashSet<LabWork> labs){
        this.labs = labs;
    }

    public void setLabs(HashSet<LabWork> labs) {
        this.labs = labs;
    }

    public HashSet<LabWork> getLabs() {
        return labs;
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
            return o1.getId() - o2.getId();
        }
    };

//    public void show(){
//        List<LabWork> labs2 = new ArrayList<>(labs);
//        labs2.sort(compareByID);
//        for (LabWork lab : labs2) {
//            System.out.println("id:" + lab.getId());
//            System.out.println("name:" + lab.getName());
//            System.out.println("coordinates: [x=" + lab.getCoordinates().getX() +
//                    ", y=" + lab.getCoordinates().getY() + "]");
//            System.out.println("minimalPoint:" + lab.getMinimalPoint());
//            System.out.println("tunedInWorks:" + lab.getTunedInWorks());
//            System.out.println("difficulty:" + lab.getDifficulty());
//            System.out.println("author: [\nname=" + lab.getAuthor().getName() +
//                    ", \nbirthday=" + lab.getAuthor().getBirthday() +
//                    "\nheight=" + lab.getAuthor().getHeight() +
//                    "\neyeColor=" + lab.getAuthor().getEyeColor() + "\n]");
//            System.out.println("---------------------");
//        }
//    }


    public void maxByAuthor() {
        List<Person> authors = new ArrayList<>();
        for (LabWork lab: labs) {
            authors.add(lab.getAuthor());
        }

        Comparator<Person> compareByName = new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };

        Person greatest = Collections.max(authors, compareByName);
        System.out.println(" ----- Автор -----");
        System.out.println("Имя: " + greatest.getName());
        System.out.println("Дата рождения: " + greatest.getBirthday());
        System.out.println("Рост: " + greatest.getHeight());
        System.out.println("Цвет глаз: " + greatest.getEyeColor());
    }


    @Override
    public String toString() {
        return "Root{" +
                ", labs=" + this.labs +
                '}';
    }
}
