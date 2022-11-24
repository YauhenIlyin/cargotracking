package by.ilyin.web.controller;

import by.ilyin.web.dto.ChangePassProfileDTO;
import by.ilyin.web.dto.UpdateUserProfileDTO;
import by.ilyin.web.dto.UserProfileDTO;
import by.ilyin.web.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileWebController {

    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfileDTO show() {
        return userProfileService.getCurrentUserProfile();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateUserProfileDTO updateUserProfileDTO,
                                       BindingResult bindingResult) {
        userProfileService.updateCurrentUserProfile(updateUserProfileDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changeProfilePassword(@RequestBody @Valid ChangePassProfileDTO changePassProfileDTO,
                                                      BindingResult bindingResult) {
        userProfileService.changePassword(changePassProfileDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

}
