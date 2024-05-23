package by.ilyin.web.security;

import by.ilyin.web.entity.CustomUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private byte[] secret;

    public String generateAccessToken(CustomUser customUser) {
        List<String> userRoleList = customUser.getUserRoles()
                .stream()
                .map(o -> o.getRoleType().toString())
                .collect(Collectors.toList());
        return JWT.create()
                .withSubject("User details")
                .withClaim("userId", customUser.getId())
                .withClaim("roles", userRoleList)
                .withIssuedAt(new Date())
                .withIssuer("Cargo tracking app")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(secret));
    }

    public String generateRefreshToken(Long userId) {
        return JWT.create()
                .withSubject("User details")
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withIssuer("Cargo tracking app")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(Algorithm.HMAC256(secret));
    }
    public DecodedJWT decodeValidateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("Cargo tracking app")
                .build();
        return verifier.verify(token);
    }

}
