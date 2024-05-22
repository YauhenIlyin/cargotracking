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

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final CustomUserDTOMapper customUserDTOMapper;
    private final CustomUserService customUserService;
    private final ProfileCoreFeignClient profileCoreFeignClient;

    public UserProfileDTO getCurrentUserProfile() {
        CustomUser customUser = getCurrentCustomUser();
        UserProfileDTO userProfileDTO = customUserDTOMapper.mapToProfileDto(customUser);
        userProfileDTO.setUserRoles(customUserService.convertRoleSetToTypeSet(customUser.getUserRoles()));
        userProfileDTO.setIssueBy(customUser.getIssuedBy()); //todo fix issueBy to issuedBy in profileDTO?
        return userProfileDTO;
    }

    //todo check if return null field, save null / old value
    public void updateCurrentUserProfile(UpdateUserProfileDTO updateUserProfileDTO) {
        profileCoreFeignClient.updateCurrentProfile(getCurrentCustomUser().getId(), updateUserProfileDTO);
    }

    //todo add an encryption mechanism and correct validation process for it
    public void changePassword(ChangePassProfileDTO changePassProfileDTO) {
        profileCoreFeignClient.changePassword(changePassProfileDTO.getNewPassword(), getCurrentCustomUser().getId());
    }

    CustomUser getCurrentCustomUser() {
        return ((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getCustomUser();
    }

}
