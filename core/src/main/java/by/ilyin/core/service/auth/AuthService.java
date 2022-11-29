package by.ilyin.core.service.auth;

import by.ilyin.core.dto.SignInDTO;
import by.ilyin.core.entity.CustomJWT;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.AuthRepository;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserRepository customUserRepository;
    private final AuthRepository authRepository;

    public CustomUser getUserByCredentials(SignInDTO signInDTO) {
        CustomUser customUser = customUserRepository.findByLogin(signInDTO.getLogin()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Incorrect login"); //todo maybe other exception and message
        });
        if (!customUser.getPassword().equals(signInDTO.getPassword())) {
            throw new ResourceNotFoundException("Incorrect password"); //todo maybe other exception and message
        }
        return customUser;
    }

    @Transactional
    public Set<CustomJWT> logoutProcess(Long userId) {
        Set<CustomJWT> jwtSet = authRepository.findAllByUserId(userId)
                .stream()
                .peek(o -> o.setIsNotBlocked(false))
                .collect(Collectors.toSet());
        authRepository.saveAll(jwtSet);
        return jwtSet;
    }

    @Transactional
    public void cleanUpExpiredJWT(LocalDateTime localDateTime) {
        authRepository.deleteAllByExpiredDateBefore(localDateTime);
    }

    public Set<CustomJWT> getJwtBlackList() {
        return new HashSet<>(authRepository.findAllByIsNotBlocked(Boolean.FALSE));
    }

    @Transactional
    public void saveJWT(CustomJWT customJwt) {
        authRepository.save(customJwt);
    }

    @Transactional
    public Set<CustomJWT> blockAccessJWT(CustomJWT refreshJWT) {
        Set<CustomJWT> blockedJWTSet =
                authRepository.findAllByUserId(refreshJWT.getUserId())
                        .stream()
                        .filter(o -> !o.getToken().equals(refreshJWT.getToken()))
                        .peek(o -> o.setIsNotBlocked(Boolean.FALSE))
                        .filter(o -> o.getIsNotBlocked() == Boolean.FALSE)
                        .collect(Collectors.toSet());
        authRepository.saveAll(blockedJWTSet);
        return blockedJWTSet;
    }

}