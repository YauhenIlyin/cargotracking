package by.ilyin.core.util.validation.custom;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.exception.http.client.ResourceAlreadyExists;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserCreateParametersValidator implements ConstraintValidator<ConsistentUpdateUserParameters, CustomUserDTO> {

    private final CustomUserRepository customUserRepository;

    @Override
    public boolean isValid(CustomUserDTO value, ConstraintValidatorContext context) {
        if (value != null) {
            customUserRepository.findByLogin(value.getLogin())
                    .orElseThrow(
                            () -> new ResourceAlreadyExists("User with login " +
                                    value.getLogin() + " already exists.")
                    );
        }
        return Boolean.TRUE;
    }

}
