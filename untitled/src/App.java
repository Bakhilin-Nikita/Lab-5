import javax.management.ObjectName;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class App {
    public static void main(String args[]) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        int i = 0;
        while (arrayList.iterator().hasNext()) {
            System.out.println(arrayList.get(i));
            i++;
        }
    }
}
