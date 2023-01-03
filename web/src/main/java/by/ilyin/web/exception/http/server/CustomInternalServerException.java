package by.ilyin.web.exception.http.server;

import by.ilyin.web.evidence.DefaultExceptionMessages;

//todo add javadoc 500
public class CustomInternalServerException extends RuntimeException {

    public CustomInternalServerException() {
        super(DefaultExceptionMessages.ACCESS_DENIED);
    }

    public CustomInternalServerException(String message) {
        super(message);
    }

    public CustomInternalServerException(Exception e) {
        super(DefaultExceptionMessages.ACCESS_DENIED, e);
    }

    public CustomInternalServerException(String message, Exception e) {
        super(message, e);
    }

}
