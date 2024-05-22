package by.ilyin.core.util.validation.custom;

import by.ilyin.core.dto.ChangeEmailDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ChangeEmailParametersValidator implements ConstraintValidator<ConsistentChangeEmailParameters, Object[]> {

    private final CustomUserRepository customUserRepository;

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        boolean isConsistentParameters = Boolean.TRUE;
        List<String> errorMessage = new ArrayList<>();
        HttpStatus httpStatus = null;
        if (value.length >= 2 &&
                value[0] instanceof Long &&
                value[1] instanceof ChangeEmailDTO) {
            Long id = (Long) value[0];
            ChangeEmailDTO changeEmailDTO = (ChangeEmailDTO) value[1];
            Optional<CustomUser> optionalUserById = customUserRepository.findById(id);
            if (optionalUserById.isEmpty()) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                errorMessage.add("Internal server error.");
                isConsistentParameters = Boolean.FALSE;
                //todo warning log
            } else {
                if (!optionalUserById.get().getPassword().equals(changeEmailDTO.getPassword())) {
                    httpStatus = HttpStatus.CONFLICT;
                    errorMessage.add("Incorrect password.");
                    isConsistentParameters = Boolean.FALSE;
                }
                if (customUserRepository.existsByEmail(changeEmailDTO.getRecipient())) {
                    httpStatus = HttpStatus.CONFLICT;
                    errorMessage.add("Email is already taken.");
                    isConsistentParameters = Boolean.FALSE;
                }
            }
            if (!isConsistentParameters) {
                throw new CustomConstraintValidationException(httpStatus, (String[]) errorMessage.toArray());
            }
        }
        return Boolean.TRUE;
    }

}