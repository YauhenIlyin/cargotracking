package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.entity.CustomUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomUserDTOMapper {

    @Mapping(target = "userRoles", ignore = true)
    CustomUserDTO mapUserToDTO(CustomUser customUser);

}
