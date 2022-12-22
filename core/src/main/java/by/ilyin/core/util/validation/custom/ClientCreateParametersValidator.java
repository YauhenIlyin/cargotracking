package by.ilyin.core.util.validation.custom;

import by.ilyin.core.dto.ClientDTO;
import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.repository.ClientRepository;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ClientCreateParametersValidator implements ConstraintValidator<ValidCreateClientData, ClientDTO> {

    private final CustomUserRepository customUserRepository;
    private final ClientRepository clientRepository;

    @Override
    public boolean isValid(ClientDTO value, ConstraintValidatorContext context) {
        if (value != null && clientRepository.existsById(value.getAdminInfo().getClientId())) {
            throw new CustomConstraintValidationException(
                    HttpStatus.CONFLICT,
                    "Client with id " + value.getAdminInfo().getClientId() + " already exists.");
        }
        if(value != null && customUserRepository.existsByLogin(value.getAdminInfo().getLogin())){
            throw new CustomConstraintValidationException(
                    HttpStatus.CONFLICT,
                    "User with login " + value.getAdminInfo().getLogin() + " already exists.");
        }
        return Boolean.TRUE;
    }

}
