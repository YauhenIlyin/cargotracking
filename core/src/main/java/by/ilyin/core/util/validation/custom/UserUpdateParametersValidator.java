package by.ilyin.core.util.validation.custom;

import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.evidence.DefaultExceptionMessages;
import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

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
        boolean isConsistentParameters = Boolean.TRUE;
        String errorMessage = null;
        HttpStatus httpStatus = null;
        if (value.length >= 2 &&
                value[0] instanceof Long &&
                value[1] instanceof UpdateUserRequestDTO) {
            Long id = (Long) value[0];
            UpdateUserRequestDTO updateUserRequestDTO = (UpdateUserRequestDTO) value[1];
            Optional<CustomUser> optionalUserById = customUserRepository.findById(id);
            if (optionalUserById.isEmpty()) {
                errorMessage = "User with id " + id + " not found.";
                httpStatus = HttpStatus.NOT_FOUND;
                isConsistentParameters = Boolean.FALSE;
            } else if (!optionalUserById.get().getLogin().equals(updateUserRequestDTO.getLogin()) &&
                    customUserRepository.existsByLogin(updateUserRequestDTO.getLogin())) {
                errorMessage = "Login " + updateUserRequestDTO.getLogin() + " is occupied by another user.";
                httpStatus = HttpStatus.CONFLICT;
                isConsistentParameters = Boolean.FALSE;
            }
        } else {
            errorMessage = DefaultExceptionMessages.INCORRECT_VALUE;
            httpStatus = HttpStatus.BAD_REQUEST;
            isConsistentParameters = Boolean.FALSE;
        }
        if (!isConsistentParameters) {
            throw new CustomConstraintValidationException(httpStatus, errorMessage);
        }
        return Boolean.TRUE;
    }

}
