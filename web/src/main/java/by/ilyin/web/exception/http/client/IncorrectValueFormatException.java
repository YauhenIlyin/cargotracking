package by.ilyin.web.exception.http.client;

import by.ilyin.web.evidence.DefaultExceptionMessages;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//todo add javadoc 400
@Getter
@Setter
public class IncorrectValueFormatException extends RuntimeException {

    private List<String> errorMessages;

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
