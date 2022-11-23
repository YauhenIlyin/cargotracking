package by.ilyin.web.controller;

import by.ilyin.web.dto.auth.LogoutDTO;
import by.ilyin.web.dto.auth.RefreshJwtDTO;
import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.service.auth.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public String signIn(@RequestBody @Valid SignInDTO signInDTO,
                         BindingResult bindingResult) {
        return authService.signInProcess(signInDTO, bindingResult);
    }

    @PostMapping("/refresh")
    public String refresh(@RequestBody @Valid RefreshJwtDTO refreshJwtDTO,
                          BindingResult bindingResult) {
        return authService.refreshProcess(refreshJwtDTO, bindingResult);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid LogoutDTO logoutDTO,
                                       BindingResult bindingResult) {
        authService.logoutProcess(logoutDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

}
