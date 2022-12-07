package by.ilyin.web.exception.http.client;

import by.ilyin.web.evidence.DefaultExceptionMessages;

//todo add javadoc 409
public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists() {
        super(DefaultExceptionMessages.RESOURCE_ALREADY_EXISTS);
    }

    public ResourceAlreadyExists(String message) {
        super(message);
    }

    public ResourceAlreadyExists(Exception e) {
        super(DefaultExceptionMessages.RESOURCE_ALREADY_EXISTS, e);
    }

    public ResourceAlreadyExists(String message, Exception e) {
        super(message, e);
    }

}
