package server71.loggers;

public interface Logger {
    void write(String text);

    void writeError(String text);

    void writeSeparator();
}
