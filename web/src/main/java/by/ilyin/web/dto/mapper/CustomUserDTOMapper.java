package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.dto.UserProfileDTO;
import by.ilyin.web.entity.CustomUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomUserDTOMapper {

    @Mapping(target = "userRoles", ignore = true)
    @Mapping(source = "client.id", target = "clientId")
    CustomUserDTO mapToDto(CustomUser customUser);

    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "issueBy", ignore = true)
    UserProfileDTO mapToProfileDto(CustomUser customUser);

    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "issuedBy", ignore = true)
    CustomUser mapFromProfileDto(UserProfileDTO userProfileDTO);

}
