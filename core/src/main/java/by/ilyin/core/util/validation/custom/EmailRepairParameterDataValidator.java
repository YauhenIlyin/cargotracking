package by.ilyin.core.util.validation.custom;

import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailRepairParameterDataValidator implements ConstraintValidator<ValidEmailRepairData, SendEmailDTO> {

    private final CustomUserRepository customUserRepository;

    @Override
    public boolean isValid(SendEmailDTO value, ConstraintValidatorContext context) {
        if (value != null && !customUserRepository.existsByEmail(value.getRecipient())) {
            throw new CustomConstraintValidationException(
                    HttpStatus.NOT_FOUND,
                    "User with email " + value + " not found.");
        }
        return Boolean.TRUE;
    }

}