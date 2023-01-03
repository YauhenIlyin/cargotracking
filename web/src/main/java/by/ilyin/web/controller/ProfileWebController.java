package by.ilyin.web.controller;

import by.ilyin.web.dto.ChangePassProfileDTO;
import by.ilyin.web.dto.request.UpdateUserProfileDTO;
import by.ilyin.web.dto.UserProfileDTO;
import by.ilyin.web.service.UserProfileService;
import by.ilyin.web.util.validation.custom.ValidChangePasswordData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/profile")
public class ProfileWebController {

    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfileDTO getCurrentProfile() {
        return userProfileService.getCurrentUserProfile();
    }

    @PutMapping
    public ResponseEntity<Void> updateCurrentProfile(@RequestBody @Valid UpdateUserProfileDTO updateUserProfileDTO) {
        userProfileService.updateCurrentUserProfile(updateUserProfileDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changeProfilePassword(@RequestBody @ValidChangePasswordData @Valid ChangePassProfileDTO changePassProfileDTO) {
        userProfileService.changePassword(changePassProfileDTO);
        return ResponseEntity
                .ok()
                .build();
    }

}
