package by.ilyin.web.exception.http.client;

import by.ilyin.web.evidence.DefaultExceptionMessages;

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
