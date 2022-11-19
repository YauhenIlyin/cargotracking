package by.ilyin.web.exception.http.client;

import by.ilyin.web.evidence.DefaultExceptionMessages;

//todo add javadoc 404
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super(DefaultExceptionMessages.RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Exception e) {
        super(DefaultExceptionMessages.RESOURCE_NOT_FOUND, e);
    }

    public ResourceNotFoundException(String message, Exception e) {
        super(message, e);
    }

}
