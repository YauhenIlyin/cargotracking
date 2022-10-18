package by.ilyin.web.exception;

public class CustomUserWebException extends Exception {

    public CustomUserWebException(Exception e) {
        super(e);
    }

    public CustomUserWebException(String message) {
        super(message);
    }

    public CustomUserWebException(String message, Exception e) {
        super(message, e);
    }

}