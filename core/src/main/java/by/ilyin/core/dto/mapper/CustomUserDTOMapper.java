package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.entity.CustomUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomUserDTOMapper {

    @Mapping(target = "userRoles", ignore = true)
    CustomUser mapDtoToUser(CustomUserDTO customUserDTO);

    @Mapping(target = "userRoles", ignore = true)
    CustomUserDTO mapUserToDto(CustomUser customUser);

}
