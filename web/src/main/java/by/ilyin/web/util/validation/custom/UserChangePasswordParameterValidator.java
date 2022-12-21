package by.ilyin.web.util.validation.custom;

import by.ilyin.web.dto.ChangePassProfileDTO;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.exception.CustomConstraintValidationException;
import by.ilyin.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserChangePasswordParameterValidator implements ConstraintValidator<ValidChangePasswordData, ChangePassProfileDTO> {

    @Override
    public boolean isValid(ChangePassProfileDTO value, ConstraintValidatorContext context) {
        if (value != null && !getCurrentCustomUser().getPassword().equals(value.getOldPassword())) {
            throw new CustomConstraintValidationException(
                    HttpStatus.BAD_REQUEST,
                    "Incorrect old password");
        }
        return Boolean.TRUE;
    }

    private CustomUser getCurrentCustomUser() {
        return ((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getCustomUser();
    }

}
