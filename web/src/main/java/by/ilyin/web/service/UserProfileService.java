package by.ilyin.web.service;

import by.ilyin.web.dto.ChangePassProfileDTO;
import by.ilyin.web.dto.request.UpdateUserProfileDTO;
import by.ilyin.web.dto.UserProfileDTO;
import by.ilyin.web.dto.mapper.CustomUserDTOMapper;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.feign.ProfileCoreFeignClient;
import by.ilyin.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import by.ilyin.web.dto.UpdateUserProfileDTO;
import by.ilyin.web.dto.UserProfileDTO;
import by.ilyin.web.dto.mapper.CustomUserDTOMapper;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.exception.http.client.IncorrectValueFormatException;
import by.ilyin.web.feign.ProfileFeignClient;
import by.ilyin.web.security.CustomUserDetails;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final CustomUserDTOMapper customUserDTOMapper;
    private final CustomUserService customUserService;
    private final CustomBindingResultValidator bindingResultValidator;
    private final ProfileFeignClient profileFeignClient;

    public UserProfileDTO getCurrentUserProfile() {
        CustomUser customUser = getCurrentCustomUser();
        UserProfileDTO userProfileDTO = customUserDTOMapper.mapToProfileDto(customUser);
        userProfileDTO.setUserRoles(customUserService.convertRoleSetToTypeSet(customUser.getUserRoles()));
        userProfileDTO.setIssueBy(customUser.getIssuedBy()); //todo fix issueBy to issuedBy in profileDTO?
        return userProfileDTO;
    }

    //todo check if return null field, save null / old value
    public void updateCurrentUserProfile(UpdateUserProfileDTO updateUserProfileDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        profileFeignClient.updateCurrentProfile(getCurrentCustomUser().getId(), updateUserProfileDTO);
    }

    //todo add an encryption mechanism and correct validation process for it
    public void changePassword(ChangePassProfileDTO changePassProfileDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        if (!getCurrentCustomUser().getPassword().equals(changePassProfileDTO.getOldPassword())) {
            throw new IncorrectValueFormatException("Incorrect old password");
        }
        profileFeignClient.changePassword(changePassProfileDTO.getNewPassword(), getCurrentCustomUser().getId());
    }

    private CustomUser getCurrentCustomUser() {
        return ((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getCustomUser();
    }

}
