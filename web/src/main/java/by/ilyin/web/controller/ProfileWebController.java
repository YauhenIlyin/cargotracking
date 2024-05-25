package by.ilyin.web.controller;

import by.ilyin.web.dto.ChangePassProfileDTO;
import by.ilyin.web.dto.request.UpdateUserProfileDTO;
import by.ilyin.web.dto.UserProfileDTO;
import by.ilyin.web.util.validation.custom.ValidChangePasswordData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import by.ilyin.web.service.UserProfileService;
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
    public ResponseEntity<Void> updateCurrentProfile(@RequestBody @Valid UpdateUserProfileDTO updateUserProfileDTO,
                                                     BindingResult bindingResult) {
        userProfileService.updateCurrentUserProfile(updateUserProfileDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changeProfilePassword(@RequestBody @ValidChangePasswordData @Valid ChangePassProfileDTO changePassProfileDTO,
                                                      BindingResult bindingResult) {
        userProfileService.changePassword(changePassProfileDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

}
