package by.ilyin.core.controller;

import by.ilyin.core.dto.SignInDTO;
import by.ilyin.core.entity.CustomJWT;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthCoreController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public CustomUser signIn(@RequestBody SignInDTO signInDTO) {
        return authService.getUserByCredentials(signInDTO);
    }

    @PostMapping("/logout")
    public Set<CustomJWT> logout(@RequestParam(value = "userId") Long userId) {
        return authService.logoutProcess(userId);
    }

    @GetMapping("/jwt")
    public Set<CustomJWT> getUpToDateBlackList() {
        return authService.getJwtBlackList();
    }

    @PostMapping("/jwt")
    public ResponseEntity<Void> saveJWT(@RequestBody CustomJWT customJWT) {
        authService.saveJWT(customJWT);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/jwt")
    public Set<CustomJWT> blockAccessJwt(@RequestBody CustomJWT refreshJWT) {
        return authService.blockAccessJWT(refreshJWT);
    }

}
