package by.ilyin.core.exception.http.client;

import by.ilyin.core.evidence.DefaultExceptionMessages;

//todo add javadoc 400
public class IncorrectValueFormatException extends RuntimeException {

    public IncorrectValueFormatException() {
        super(DefaultExceptionMessages.INCORRECT_VALUE);
    }

    public IncorrectValueFormatException(String message) {
        super(message);
    }

    public IncorrectValueFormatException(Exception e) {
        super(DefaultExceptionMessages.INCORRECT_VALUE, e);
    }

    public IncorrectValueFormatException(String message, Exception e) {
        super(message, e);
    }

}
