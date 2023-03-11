package org.example.lab5.commands;

import org.example.lab5.ElementCommand;
import org.example.lab5.parserFromJson.Root;

import java.io.IOException;
import java.text.ParseException;

public class AddIfMaxCommand implements ElementCommand {
    private Root root;

    public AddIfMaxCommand(Root root){
        this.root = root;
    }

    @Override
    public void execute(String e) {
        try {
            root.addIfMax(e);
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
