package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetClientByIdResponseDTO;
import by.ilyin.web.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientDTOMapper {

    GetClientByIdResponseDTO mapToDto(Client client);

}
