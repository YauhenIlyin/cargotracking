package by.ilyin.core.util.validation.custom;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserCreateParametersValidator implements ConstraintValidator<ValidCreateUserData, CustomUserDTO> {

    private final CustomUserRepository customUserRepository;

    @Override
    public boolean isValid(CustomUserDTO value, ConstraintValidatorContext context) {
        if (value != null && customUserRepository.existsByLogin(value.getLogin())) {
            throw new CustomConstraintValidationException(
                    HttpStatus.CONFLICT,
                    "User with login " + value.getLogin() + " already exists.");
        }
        return Boolean.TRUE;
    }

}
