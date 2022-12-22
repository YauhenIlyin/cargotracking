package by.ilyin.core.util.validation.custom;

import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ClientIdByExistsParameterValidator implements ConstraintValidator<ValidIdByClientExists, Long> {

    private final ClientRepository clientRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value != null && !clientRepository.existsById(value)) {
            throw new CustomConstraintValidationException(
                    HttpStatus.NOT_FOUND,
                    "Client with id " + value + " not found.");
        }
        return Boolean.TRUE;
    }

}