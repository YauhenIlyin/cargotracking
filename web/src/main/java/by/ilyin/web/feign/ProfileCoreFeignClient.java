package by.ilyin.web.feign;

import by.ilyin.web.dto.request.UpdateUserProfileDTO;
import by.ilyin.web.entity.CustomUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profileCoreFeignClient", url = "${feign.client.core.url}")
public interface ProfileCoreFeignClient {

    @PutMapping(value = "/api/profile", consumes = "application/json")
    CustomUser updateCurrentProfile(@RequestParam("userId") Long userId,
                                    UpdateUserProfileDTO updateUserProfileDTO);

    @PutMapping(value = "/api/profile/change-password", consumes = "application/json")
    void changePassword(@RequestParam("updatedPassword") String updatedPassword,
                        @RequestParam("userId") Long userId);

}
