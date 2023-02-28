package lab5.command.show;

import lab5.Controller;
import lab5.features.LabWork;
import lab5.command.Command;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;

public class Show implements Command {
    private Command showTheSummary;

    public Show() {
    }

    public Show(Command showTheSummary) {
        this.showTheSummary = showTheSummary;
    }

    public void show() throws FileNotFoundException {
        HashSet<LabWork> labs = new Controller().getLabWorks();
        for (LabWork lab : labs) {
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

    @Override
    public void execute() throws FileNotFoundException {
        show();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Show show = (Show) o;

        return show.showTheSummary == this.showTheSummary;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(showTheSummary);
    }
}