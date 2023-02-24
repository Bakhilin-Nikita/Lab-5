package org.example.example.lab5;

import org.example.lab5.exceptions.InvalidFieldY;
import org.example.lab5.exceptions.NullX;
import org.example.lab5.parserFromJson.Root;
import org.example.lab5.parserToJson.ParserToJson;
import org.example.lab5.workWithCollections.*;

import java.util.HashSet;

/**
 * Kernel Class of this program by ...
 */

public class App {

    public static void main(String[] args) throws NullX, InvalidFieldY {

        Root root = new Root();
        HashSet<LabWork> labs = new HashSet<>();
        root.setLabs(labs);

        //to Json
        ParserToJson inJson = new ParserToJson();
        LabWork lab1 = inJson.addToList("Rim", 123, 12, "EASY", 11, 12.3, "Nikita", "GREEN", 224, "23-05-2004");
        LabWork lab2 = inJson.addToList("Rim21", 123, 121, "EASY", 11, 12.3, "George", "GREEN", 224, "23-05-2004");
        LabWork lab3 = inJson.addToList("Ri342m21", 123423, 4312, "EASY", 11, 12.3, "Daniel", "GREEN", 224, "23-05-2004");

        ElementCommand addEl = new AddNewElementCommand(root);
        Add a = new Add(addEl);

        Command showLabs = new ShowTheCollectionCommand(root);
        Show s = new Show(showLabs);

        IDCommand removeEl = new RemoveElementByIDCommand(root);
        Remove r = new Remove(removeEl);

        Command clearLabs = new ClearTheCollectionCommand(root);
        Clear c = new Clear(clearLabs);

        Command printTiW = new PrintUniqueTiWCommand(root);
        UniqueTiW unique = new UniqueTiW(printTiW);

        Command sortTiW = new PrintSortedTiWCommand(root);
        SortedTiW sort = new SortedTiW(sortTiW);

        Command maxByAuthor = new MaxByAuthorCommand(root);
        AuthorMaximization author = new AuthorMaximization(maxByAuthor);

        ElementCommand addElIfMax = new AddIfMaxCommand(root);
        AddIfMax addMax = new AddIfMax(addElIfMax);

        ElementCommand removeGreaterEl = new RemoveGreaterElementCommand(root);
        RemoveGreater greater = new RemoveGreater(removeGreaterEl);

        ElementCommand removeLowerEl = new RemoveLowerElementCommand(root);
        RemoveLower lower = new RemoveLower(removeLowerEl);

        UpdateCommand updateEl = new UpdateElementCommand(root);
        Update update = new Update(updateEl);

        a.add(lab1);
        a.add(lab2);
        addMax.addIfMax(lab3);


        //greater.removeGreater(lab2);
        //lower.removeLower(lab2);

        //update.update(2,lab3);

        //unique.printUniqueTunedInWorks();
        //sort.printFieldAscendingTunedInWorks();
        //author.maxByAuthor();
        s.show();
    }
}
