package server7.exceptions;

import java.io.IOException;

public class WrongCommandInputException extends IOException {
    public WrongCommandInputException(String message) {
        super(message);
    }
}