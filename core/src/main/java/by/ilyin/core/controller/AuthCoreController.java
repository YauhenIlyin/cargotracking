package by.ilyin.core.controller;

import by.ilyin.core.dto.SignInDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthCoreController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public CustomUser signIn(@RequestBody SignInDTO signInDTO) {
        return authService.getUserByCredentials(signInDTO);
    }

}
