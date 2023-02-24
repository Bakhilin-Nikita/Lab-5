package org.example.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class SortedTiW {
    private Command sortTiW;

    public SortedTiW(Command sortTiW){
        this.sortTiW = sortTiW;
    }

    public void printFieldAscendingTunedInWorks(){
        sortTiW.execute();
    }
}
