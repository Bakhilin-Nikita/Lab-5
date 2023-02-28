package lab5.command;


import java.io.FileNotFoundException;

/**
 * command interface
 */

public interface Command {
    void execute() throws FileNotFoundException;
}
