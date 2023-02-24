package org.example.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class UniqueTiW {
    private Command printTiW;

    public UniqueTiW(Command printTiW){
        this.printTiW = printTiW;
    }

    public void printUniqueTunedInWorks(){
        printTiW.execute();
    }

}
