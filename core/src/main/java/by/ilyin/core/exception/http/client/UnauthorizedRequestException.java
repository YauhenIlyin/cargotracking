package by.ilyin.core.exception.http.client;

import by.ilyin.core.evidence.DefaultExceptionMessages;

//todo add javadoc 401
public class UnauthorizedRequestException extends RuntimeException {

    public UnauthorizedRequestException() {
        super(DefaultExceptionMessages.UNAUTHORIZED_REQUEST);
    }

    public UnauthorizedRequestException(String message) {
        super(message);
    }

    public UnauthorizedRequestException(Exception e) {
        super(DefaultExceptionMessages.UNAUTHORIZED_REQUEST, e);
    }

    public UnauthorizedRequestException(String message, Exception e) {
        super(message, e);
    }

}
