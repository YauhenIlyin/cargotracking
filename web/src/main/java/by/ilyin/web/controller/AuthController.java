package by.ilyin.web.controller;

import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public String refresh(@RequestParam(value = "userId") @NotNull Long userId,
                          @RequestParam(value = "token") String token) {
        return authService.refreshProcess(userId, token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam(value = "userId") Long userId) {
        authService.logoutProcess(userId);
        return ResponseEntity
                .ok()
                .build();
    }

}
