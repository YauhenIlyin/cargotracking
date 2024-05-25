package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetClientByIdResponseDTO;
import by.ilyin.web.entity.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ClientDTOMapperImpl implements ClientDTOMapper {

    @Override
    public GetClientByIdResponseDTO mapToDto(Client client) {
        if ( client == null ) {
            return null;
        }

        GetClientByIdResponseDTO getClientByIdResponseDTO = new GetClientByIdResponseDTO();

        getClientByIdResponseDTO.setId( client.getId() );
        getClientByIdResponseDTO.setName( client.getName() );
        getClientByIdResponseDTO.setStatus( client.getStatus() );
        getClientByIdResponseDTO.setDeleteDate( client.getDeleteDate() );

        return getClientByIdResponseDTO;
    }
}
