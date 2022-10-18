package by.ilyin.web.exception;

public class IncorrectDTOException extends CustomUserWebException {

    public IncorrectDTOException(Exception e) {
        super(e);
    }

    public IncorrectDTOException(String message) {
        super(message);
    }

    public IncorrectDTOException(String message, Exception e) {
        super(message, e);
    }

}