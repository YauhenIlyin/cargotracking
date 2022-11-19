package by.ilyin.core.util.validation;

import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final CustomUserRepository customUserRepository;

    public boolean isUserLoginAlreadyExists(String login) {
        return customUserRepository.findByLogin(login).isPresent();
    }

    public boolean isCorrectLoginWithId(Long id, String login) {
        return isCorrectLoginWithId(
                customUserRepository.findById(id),
                customUserRepository.findByLogin(login));
    }

    public boolean isCorrectLoginWithId(Optional<CustomUser> optionalUserById, Optional<CustomUser> optionalUserByLogin) {
        boolean result = false;
        if (optionalUserByLogin.isEmpty()) {
            result = true;
        } else if (optionalUserById.isPresent()) {
            CustomUser userById = optionalUserById.get();
            CustomUser userByLogin = optionalUserByLogin.get();
            result = userById.getId().equals(userByLogin.getId())
                    ? Boolean.TRUE : Boolean.FALSE;
        }
        return result;
    }

}
