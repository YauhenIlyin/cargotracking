package by.ilyin.web.service.auth;

import by.ilyin.web.dto.auth.RefreshJwtDTO;
import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.exception.http.client.ResourceNotFoundException;
import by.ilyin.web.feign.AuthCoreFeignClient;
import by.ilyin.web.feign.UsersCoreFeignClient;
import by.ilyin.web.security.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthCoreFeignClient authCoreFeignClient;
    private final JwtUtil jwtUtil;
    private final UsersCoreFeignClient usersCoreFeignClient;

    //todo need logout with next sign-in ?
    public String signInProcess(SignInDTO signInDTO, BindingResult bindingResult) {
        customBindingResultValidator.validationProcess(bindingResult);
        CustomUser customUser = authCoreFeignClient.signIn(signInDTO);
        return new StringBuilder()
                .append(jwtUtil.generateAccessToken(customUser))
                .append(" ")
                .append(jwtUtil.generateRefreshToken(customUser.getId()))
                .toString();
    }

    public String refreshProcess(RefreshJwtDTO refreshJwtDTO) {
        DecodedJWT decodedJWT = jwtUtil.decodeValidateToken(refreshJwtDTO.getToken());
        Long tokenUserId = decodedJWT.getClaim("userId").as(Long.class);
        if (!tokenUserId.equals(refreshJwtDTO.getUserId())) {
            throw new ResourceNotFoundException("Incorrect userId");
        }
        CustomUser customUser = usersCoreFeignClient.getUserById(refreshJwtDTO.getUserId());
        String currentAccessJWT = jwtUtil.generateAccessToken(customUser);
        return new StringBuilder()
                .append(currentAccessJWT)
                .append(" ")
                .append(refreshJwtDTO.getToken())
                .toString();
    }

}
