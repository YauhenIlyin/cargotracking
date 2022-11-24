package by.ilyin.core.service;

import by.ilyin.core.dto.request.UpdateUserProfileDTO;
import by.ilyin.core.dto.UpdateUserProfileDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final CustomUserRepository customUserRepository;

    //todo check if return null field, save null / old value?
    public void updateCurrentUserProfile(Long userId, UpdateUserProfileDTO updateProfileDTO) {
        CustomUser customUser = customUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile update failed. User was not found."));
        if (updateProfileDTO.getName() != null) {
            customUser.setName(updateProfileDTO.getName());
        }
        if (updateProfileDTO.getSurname() != null) {
            customUser.setSurname(updateProfileDTO.getSurname());
        }
        if (updateProfileDTO.getPatronymic() != null) {
            customUser.setPatronymic(updateProfileDTO.getPatronymic());
        }
        if (updateProfileDTO.getBornDate() != null) {
            customUser.setBornDate(updateProfileDTO.getBornDate());
        }
        if (updateProfileDTO.getTown() != null) {
            customUser.setTown(updateProfileDTO.getTown());
        }
        if (updateProfileDTO.getStreet() != null) {
            customUser.setStreet(updateProfileDTO.getStreet());
        }
        if (updateProfileDTO.getHouse() != null) {
            customUser.setHouse(updateProfileDTO.getHouse());
        }
        if (updateProfileDTO.getFlat() != null) {
            customUser.setFlat(updateProfileDTO.getFlat());
        }
        customUserRepository.save(customUser);
    }

    public void changePassword(String updatedPassword, Long userId) {
        CustomUser customUser = customUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Password change process failed. Profile with id " +
                        userId + " was not found."));
        customUser.setPassword(updatedPassword);
        customUserRepository.save(customUser);
    }

}
