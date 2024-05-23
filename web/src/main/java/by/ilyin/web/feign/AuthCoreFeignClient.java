package by.ilyin.web.feign;

import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.entity.CustomUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import by.ilyin.web.entity.CustomJWT;
import by.ilyin.web.entity.CustomUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;

@FeignClient(name = "authCoreFeignClient", url = "${feign.client.core.url}")
public interface AuthCoreFeignClient {

    @PostMapping(value = "/api/sign-in", consumes = "application/json")
    CustomUser signIn(SignInDTO signInDTO);

    @PostMapping(value = "/api/logout", consumes = "application/json")
    HashSet<CustomJWT> logoutProcess(@RequestParam("userId") Long userId);

    @GetMapping(value = "/api/jwt", consumes = "application/json")
    HashSet<CustomJWT> getUpToDateBlackList();

    @PostMapping(value = "/api/jwt", consumes = "application/json")
    ResponseEntity<Void> saveJWT(CustomJWT customJWT);

    @PutMapping(value = "/api/jwt", consumes = "application/json")
    HashSet<CustomJWT> blockAccessJWT(CustomJWT refreshJWT);

}
