package parser;

import object.LabWork;
import parser.parserFromJson.ParserFromJson;

import java.util.HashSet;


public class Root {

    private ParserFromJson parser = new ParserFromJson();
    private HashSet<LabWork> labWorkSet = new HashSet<>();

    private ParserFromJson getParser() {
        return parser;
    }

    public void setParser(ParserFromJson parser) {
        this.parser = parser;
    }

    public void setLabWorkSet(HashSet<LabWork> labWorkSet) {
        this.labWorkSet = labWorkSet;
    }

    public HashSet<LabWork> getLabWorkSet() {
        return labWorkSet;
    }

    //to String. Пусть будет.
    @Override
    public String toString() {
        return "Root{" +
                ", labs=" + this.labWorkSet +
                '}';
    }
}
