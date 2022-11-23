package by.ilyin.web.service.auth;

import by.ilyin.web.dto.auth.RefreshJwtDTO;
import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.entity.CustomJWT;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.exception.http.client.ResourceNotFoundException;
import by.ilyin.web.feign.AuthCoreFeignClient;
import by.ilyin.web.feign.UsersCoreFeignClient;
import by.ilyin.web.security.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import by.ilyin.web.security.JwtBlackListManager;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthCoreFeignClient authCoreFeignClient;
    private final JwtUtil jwtUtil;
    private final UsersCoreFeignClient usersCoreFeignClient;
    private final CustomBindingResultValidator customBindingResultValidator;
    private JwtBlackListManager jwtBlackListManager;
    private JwtBlackListManager jwtBlackListManager;
    private final UsersCoreFeignClient usersCoreFeignClient;
    private final CustomBindingResultValidator customBindingResultValidator;
    private JwtBlackListManager jwtBlackListManager;

    @Autowired
    public void setJwtBlackListManager(JwtBlackListManager jwtBlackListManager) {
        this.jwtBlackListManager = jwtBlackListManager;
    }

    //todo need logout with next sign-in ?
    public String signInProcess(SignInDTO signInDTO, BindingResult bindingResult) {
        customBindingResultValidator.validationProcess(bindingResult);
        CustomUser customUser = authCoreFeignClient.signIn(signInDTO);
        String accessToken = jwtUtil.generateAccessToken(customUser);
        String refreshToken = jwtUtil.generateRefreshToken(customUser.getId());
        authCoreFeignClient.saveJWT(jwtUtil.buildCustomJwt(accessToken));
        authCoreFeignClient.saveJWT(jwtUtil.buildCustomJwt(refreshToken));
        return new StringBuilder()
                .append(accessToken)
                .append(" ")
                .append(refreshToken)
                .toString();
    }

    public String refreshProcess(Long userId, String refreshToken) {
        DecodedJWT decodedJWT = jwtUtil.decodeValidateToken(refreshToken);
        Long tokenUserId = decodedJWT.getClaim("userId").as(Long.class);
        if (!tokenUserId.equals(userId)) {
            throw new ResourceNotFoundException("Incorrect userId");
        }
        CustomUser customUser = usersCoreFeignClient.getUserById(userId);
        Set<CustomJWT> blockedJWTSet = authCoreFeignClient.blockAccessJWT(jwtUtil.buildCustomJwt(refreshToken));
        jwtBlackListManager.addToBlackList(blockedJWTSet);
        String currentAccessJWT = jwtUtil.generateAccessToken(customUser);
        authCoreFeignClient.saveJWT(jwtUtil.buildCustomJwt(currentAccessJWT));
        return new StringBuilder()
                .append(currentAccessJWT)
                .append(" ")
                .append(refreshToken)
                .toString();
    }

    public void logoutProcess(Long id) {
        Set<CustomJWT> blockedJWTSet = authCoreFeignClient.logoutProcess(id);
        jwtBlackListManager.addToBlackList(blockedJWTSet);
    }

    public Set<CustomJWT> getUpToDateBlackList() {
        return authCoreFeignClient.getUpToDateBlackList();
    }

}
