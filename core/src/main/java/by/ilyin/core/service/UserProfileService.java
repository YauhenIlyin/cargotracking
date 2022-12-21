package by.ilyin.core.service;

import by.ilyin.core.dto.request.UpdateUserProfileDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final CustomUserRepository customUserRepository;

    //todo check if return null field, save null / old value?
    public void updateCurrentUserProfile(Long userId, UpdateUserProfileDTO updateProfileDTO) {
        CustomUser customUser = customUserRepository.findById(userId).orElseThrow();
        customUser.setName(updateProfileDTO.getName());
        customUser.setSurname(updateProfileDTO.getSurname());
        customUser.setPatronymic(updateProfileDTO.getPatronymic());
        customUser.setBornDate(updateProfileDTO.getBornDate());
        customUser.setTown(updateProfileDTO.getTown());
        customUser.setStreet(updateProfileDTO.getStreet());
        customUser.setHouse(updateProfileDTO.getHouse());
        customUser.setFlat(updateProfileDTO.getFlat());
        customUserRepository.save(customUser);
    }

    public void changePassword(String updatedPassword, Long userId) {
        CustomUser customUser = customUserRepository.findById(userId).orElseThrow();
        customUser.setPassword(updatedPassword);
        customUserRepository.save(customUser);
    }

}
