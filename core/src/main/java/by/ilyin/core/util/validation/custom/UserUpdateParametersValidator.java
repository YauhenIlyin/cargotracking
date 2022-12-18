package by.ilyin.core.util.validation.custom;

import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.exception.http.client.ResourceAlreadyExists;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Optional;

@RequiredArgsConstructor
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class UserUpdateParametersValidator implements ConstraintValidator<ConsistentUpdateUserParameters, Object[]> {

    private final CustomUserRepository customUserRepository;

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if (value[0] != null && value[1] != null &&
                value[0] instanceof Long &&
                value[1] instanceof UpdateUserRequestDTO) {
            Long id = (Long) value[0];
            UpdateUserRequestDTO updateUserRequestDTO = (UpdateUserRequestDTO) value[1];
            if (!customUserRepository.existsById(id)) {
                throw new ResourceNotFoundException("User with id " + id + " not found.");
            }
            Optional<CustomUser> optionalUserByLogin = customUserRepository.findByLogin(updateUserRequestDTO.getLogin());
            if (optionalUserByLogin.isPresent() && !optionalUserByLogin.get().getId().equals(id)) {
                throw new ResourceAlreadyExists(
                        "Login " + updateUserRequestDTO.getLogin() + " is occupied by another user.");
            }
        }
        return Boolean.TRUE;
    }

}
