package org.example.example.lab5.workWithCollections;

import org.example.lab5.IDCommand;

public class Remove {
    private IDCommand removeElement;

    public Remove(IDCommand removeElement){
        this.removeElement = removeElement;
    }

    public void remove(int id){
        removeElement.execute(id);
    }
}