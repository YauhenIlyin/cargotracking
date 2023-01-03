package by.ilyin.core.service.auth;

import by.ilyin.core.dto.SignInDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserRepository customUserRepository;

    public CustomUser getUserByCredentials(SignInDTO signInDTO) {
        CustomUser customUser = customUserRepository.findByLogin(signInDTO.getLogin()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Incorrect login"); //todo maybe other exception and message
        });
        if (!customUser.getPassword().equals(signInDTO.getPassword())) {
            throw new ResourceNotFoundException("Incorrect password"); //todo maybe other exception and message
        }
        return customUser;
    }

}
