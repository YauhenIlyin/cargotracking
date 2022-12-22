package by.ilyin.core.exception.http;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintDeclarationException;

@NoArgsConstructor
@Getter
public class CustomConstraintValidationException extends ConstraintDeclarationException {

    private HttpStatus httpStatus;
    private String[] messages;

    public CustomConstraintValidationException(HttpStatus httpStatus, String... messages) {
        this.httpStatus = httpStatus;
        this.messages = messages;
    }

    public void setMessages(String... messages) {
        this.messages = messages;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
