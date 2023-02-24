package org.example.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class Show {
    private Command showTheSummary;

    public Show(Command showTheSummary){
        this.showTheSummary = showTheSummary;
    }

    public void show(){
        showTheSummary.execute();
    }
}
