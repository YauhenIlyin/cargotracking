package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.request.CreateUserRequestDTO;
import by.ilyin.core.dto.request.UpdateUserRequestDTO;
import by.ilyin.core.dto.response.GetUserResponseDTO;
import by.ilyin.core.entity.CustomUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomUserDTOMapper {

    @Mapping(target = "userRoles", ignore = true)
    CustomUser createUserRequestDtoToCustomUser(CreateUserRequestDTO createUserRequestDTO);

    @Mapping(target = "userRoles", ignore = true)
    CreateUserRequestDTO customUserToCreateUserRequestDto(CustomUser customUser);

    CustomUser updateUserRequestDtoToCustomUser(UpdateUserRequestDTO updateUserRequestDTO);

    @Mapping(target = "userRoles", ignore = true)
    GetUserResponseDTO customUserToGetUserResponseDTO(CustomUser customUser);


}
