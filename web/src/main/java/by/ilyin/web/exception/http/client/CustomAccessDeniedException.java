package by.ilyin.web.exception.http.client;

import by.ilyin.web.evidence.DefaultExceptionMessages;

//todo javadoc 403
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
