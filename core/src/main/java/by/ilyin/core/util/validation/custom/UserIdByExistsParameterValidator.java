package by.ilyin.core.util.validation.custom;

import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserIdByExistsParameterValidator implements ConstraintValidator<ValidIdByUserExists, Long> {

    private final CustomUserRepository customUserRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value != null && !customUserRepository.existsById(value)) {
            throw new CustomConstraintValidationException(
                    HttpStatus.NOT_FOUND,
                    "User with id " + value + " not found.");
        }
        return Boolean.TRUE;
    }

}