package by.ilyin.web.security;

import by.ilyin.web.entity.CustomJWT;
import by.ilyin.web.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtBlackListManager {

    private AuthService authService;
    private Set<String> blackList = new HashSet<>();

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void addToBlackList(CustomJWT customJwt) {
        this.blackList.add(customJwt.getToken());
    }

    public void addToBlackList(Collection<CustomJWT> blockedJWTCollection) {
        blockedJWTCollection.stream()
                .peek(o -> blackList.add(o.getToken()))
                .close();
    }

    public boolean isValid(CustomJWT customJwt) {
        return isValid(customJwt.getToken());
    }

    public boolean isValid(String token) {
        return !this.blackList.contains(token);
    }

    public void updateBlackList() {
        this.blackList = authService.getUpToDateBlackList()
                .stream()
                .map(CustomJWT::getToken)
                .collect(Collectors.toSet());
    }

}
