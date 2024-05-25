package by.ilyin.core.controller;

import by.ilyin.core.dto.request.UpdateUserProfileDTO;
import by.ilyin.core.service.UserProfileService;
import by.ilyin.core.util.validation.custom.ValidIdByUserExists;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileCoreController {

    private final UserProfileService userProfileService;

    @PutMapping
    void updateCurrentProfile(@RequestParam("userId") @ValidIdByUserExists Long userId,
                              @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
        userProfileService.updateCurrentUserProfile(userId, updateUserProfileDTO);
    }

    //todo char[] password
    @PutMapping("/change-password")
    void changePassword(@RequestParam("updatedPassword") String updatedPassword,
                        @RequestParam("userId") @ValidIdByUserExists Long userId) {
        userProfileService.changePassword(updatedPassword, userId);
    }

}
