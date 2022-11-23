package by.ilyin.web.controller;

import by.ilyin.web.dto.auth.LogoutDTO;
import by.ilyin.web.dto.auth.RefreshJwtDTO;
import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    //todo add @Valid
    //todo string to byte[];
    @PostMapping("/sign-in")
    public String signIn(@RequestBody @Valid SignInDTO signInDTO) {
        return authService.signInProcess(signInDTO);
    }

    @PostMapping("/refresh")
    public String refresh(@RequestBody @Valid RefreshJwtDTO refreshJwtDTO) {
        return authService.refreshProcess(refreshJwtDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid LogoutDTO logoutDTO) {
        return ResponseEntity
                .ok()
                .build();
    }

}
