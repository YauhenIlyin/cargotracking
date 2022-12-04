package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ClientDTO;
import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.entity.Client;
import by.ilyin.core.entity.CustomUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientDTOMapper {

    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "client1", ignore = true)
    CustomUser mapFromCustomUserDto(CustomUserDTO customUserDTO);

    @Mapping(source = "adminInfo", target = "generalAdmin")
    Client mapFromDto(ClientDTO clientDTO);

}
