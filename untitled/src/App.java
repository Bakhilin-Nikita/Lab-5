import javax.management.ObjectName;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class App {
    public static void main(String args[]) {
        String[] arrCity = {"Astrakhan", "Moscow", "Chicago"};

        ArrayList<String> states = new ArrayList<String>();
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        states.add("Spain");

        for (String state : states) System.out.println(state);

    }
}
