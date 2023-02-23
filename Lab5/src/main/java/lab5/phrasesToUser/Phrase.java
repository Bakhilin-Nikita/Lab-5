package lab5.phrasesToUser;

import lab5.features.enums.Difficulty;

public class Phrase {
    public static void addOrNot() {
        System.out.println("Do you want to add new labWork in collection? (1) - Yes (2) - No");
    }

    public static void nameLab() {
        System.out.println("Name of your Lab: ");
    }

    public static void minimalPoint() {
        System.out.println("Enter the Minimal Point: ");
    }

    public static void tunedInWorks() {
        System.out.println("Tuned in works:");
    }

    public static void difficulty(){
        System.out.println("Choose the DIFFICULTY:");
        for (Difficulty diff: Difficulty.values())
            System.out.println(diff);
    }
}
