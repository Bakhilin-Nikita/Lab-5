package org.example.example.lab5;

public interface UpdateCommand extends Command{

    void execute(int id, LabWork e);
}
