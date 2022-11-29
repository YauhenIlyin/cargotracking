package by.ilyin.core.exception.http.client;

import by.ilyin.core.evidence.DefaultExceptionMessages;

//todo add javadoc 403
public class CustomAccessDeniedException extends RuntimeException {

    public CustomAccessDeniedException() {
        super(DefaultExceptionMessages.ACCESS_DENIED);
    }

    public CustomAccessDeniedException(String message) {
        super(message);
    }

    public CustomAccessDeniedException(Exception e) {
        super(DefaultExceptionMessages.ACCESS_DENIED, e);
    }

    public CustomAccessDeniedException(String message, Exception e) {
        super(message, e);
    }

}
