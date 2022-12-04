package by.ilyin.core.exception.http.server;

import by.ilyin.core.evidence.DefaultExceptionMessages;

//todo add javadoc 501
public class NotImplementedMethodException extends RuntimeException {

    public NotImplementedMethodException() {
        super(DefaultExceptionMessages.NOT_IMPLEMENTED_METHOD);
    }

    public NotImplementedMethodException(String message) {
        super(message);
    }

    public NotImplementedMethodException(Exception e) {
        super(DefaultExceptionMessages.NOT_IMPLEMENTED_METHOD, e);
    }

    public NotImplementedMethodException(String message, Exception e) {
        super(message, e);
    }

}
