package by.ilyin.web.util.validator;

import by.ilyin.web.exception.http.client.IncorrectValueFormatException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public void userValidationProcess(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError currentError : errors) {
                errorMessages.add(currentError.getDefaultMessage());
            }
            IncorrectValueFormatException exception = new IncorrectValueFormatException();
            exception.setErrorMessages(errorMessages);
            throw exception;
        }
    }

}
