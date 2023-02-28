package lab5.command;

import lab5.features.LabWork;

public interface ElementCommand extends Command{
    void execute(LabWork e);
}
