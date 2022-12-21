package by.ilyin.web.feign;

import by.ilyin.web.dto.auth.SignInDTO;
import by.ilyin.web.entity.CustomUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "authCoreFeignClient", url = "${feign.client.core.url}")
public interface AuthCoreFeignClient {

    @PostMapping(value = "/api/sign-in", consumes = "application/json")
    CustomUser signIn(SignInDTO signInDTO);

}
