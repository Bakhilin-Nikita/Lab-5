package org.example.example.lab5.workWithCollections;

import org.example.lab5.Command;

public class AuthorMaximization {
    private Command maxAuthorCommand;

    public AuthorMaximization(Command maxAuthorCommand){
        this.maxAuthorCommand = maxAuthorCommand;
    }

    public void maxByAuthor(){
       maxAuthorCommand.execute();
    }
}
