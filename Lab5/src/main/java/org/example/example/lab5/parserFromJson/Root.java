package org.example.example.lab5.parserFromJson;

import org.example.lab5.LabWork;
import org.example.lab5.features.Person;

import java.util.*;

public class Root {
    private HashSet<LabWork> labs;

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
            return o1.getId()-o2.getId();
        }
    };

    public void setLabs(HashSet<LabWork> labs) {
        this.labs = labs;
    }

    public HashSet<LabWork> getLabs() {
        return labs;
    }

    @Override
    public String toString() {
        return "Root{" +
                ", labs=" + this.labs +
                '}';
    }

    public void show(){
        List<LabWork> labs2 = new ArrayList<>();
        for (LabWork lab : labs) {
            labs2.add(lab);
        }
        labs2.sort(compareByID);
        for (LabWork lab : labs2) {
            System.out.println("id:" + lab.getId());
            System.out.println("name:" + lab.getName());
            System.out.println("coordinates: [x=" + lab.getCoordinates().getX() +
                    ", y=" + lab.getCoordinates().getY() + "]");
            System.out.println("minimalPoint:" + lab.getMinimalPoint());
            System.out.println("tunedInWorks:" + lab.getTunedInWorks());
            System.out.println("difficulty:" + lab.getDifficulty());
            System.out.println("author: [\nname=" + lab.getAuthor().getName() +
                    ", \nbirthday=" + lab.getAuthor().getBirthday() +
                    "\nheight=" + lab.getAuthor().getHeight() +
                    "\neyeColor=" + lab.getAuthor().getEyeColor() + "\n]");
            System.out.println("---------------------");
        }
    }

    public void addEl(LabWork e) {
        labs.add(e);
    }

    public void removeEl(int id){
        int flag = 0;
        for (LabWork lab : labs) {
            if (lab.getId() == id){
                labs.remove(lab);
                flag = 1;
                break;
            }
        }

        if (flag == 0){
            System.out.println("Элемент с данным id не найден!");
        }
    }

    public void clearCollection(){
        labs.clear();
    }

    public void printUniqueTunedInWorks(){
        Set<Integer> unique = new HashSet<>();
        for (LabWork lab: labs){
            unique.add(lab.getTunedInWorks());
        }
        for (int el: unique){
            System.out.print(el+" ");
        }
        System.out.println("\n");
    }

    public void printFieldAscendingTunedInWorks(){
        List<Integer> tunedInWorks = new LinkedList<>();
        for (LabWork lab: labs){
            tunedInWorks.add(lab.getTunedInWorks());
        }
        Collections.sort(tunedInWorks);
        for (int el: tunedInWorks){
            System.out.print(el+" ");
        }
        System.out.println("\n");
    }

    public void maxByAuthor(){
        List<Person> authors = new ArrayList<>();
        for (LabWork lab: labs){
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

    public void addIfMax(LabWork e){
        LabWork maximum = Collections.max(labs,compareByMinPoint);
        if ((e.getMinimalPoint() - maximum.getMinimalPoint()) > 0){
            labs.add(e);
        }
    }

    public void removeGreater(LabWork e){
        List<LabWork> labWorks = new LinkedList<>();
        for (LabWork lab:labs){
            labWorks.add(lab);
        }
        labWorks.sort(compareByMinPointReverse);
        for (LabWork el:labWorks) {
            if(el == e){break;}
            labs.remove(el);
        }
    }

    public void removeLower(LabWork e){
        List<LabWork> labWorks = new LinkedList<>();
        for (LabWork lab:labs){
            labWorks.add(lab);
        }
        labWorks.sort(compareByMinPoint);
        for (LabWork el:labWorks) {
            if(el == e){break;}
            labs.remove(el);
        }
    }

    public void update(int id, LabWork e){
        int flag = 0;
        for (LabWork lab:labs){
            if (lab.getId() == id){
                labs.remove(lab);
                labs.add(e);
                e.setId(id);
                flag = 1;
                break;
            }
            if (flag == 0){
                System.out.println(" Элемент с данным ID отсутствует ");
            }
        }
    }
}
