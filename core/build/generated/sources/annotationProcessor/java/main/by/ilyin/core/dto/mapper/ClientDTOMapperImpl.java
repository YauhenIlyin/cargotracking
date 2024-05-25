package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ClientDTO;
import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.entity.Client;
import by.ilyin.core.entity.CustomUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:42+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ClientDTOMapperImpl implements ClientDTOMapper {

    @Override
    public CustomUser mapFromCustomUserDto(CustomUserDTO customUserDTO) {
        if ( customUserDTO == null ) {
            return null;
        }

        CustomUser customUser = new CustomUser();

        customUser.setName( customUserDTO.getName() );
        customUser.setSurname( customUserDTO.getSurname() );
        customUser.setPatronymic( customUserDTO.getPatronymic() );
        customUser.setBornDate( customUserDTO.getBornDate() );
        customUser.setEmail( customUserDTO.getEmail() );
        customUser.setTown( customUserDTO.getTown() );
        customUser.setStreet( customUserDTO.getStreet() );
        customUser.setHouse( customUserDTO.getHouse() );
        customUser.setFlat( customUserDTO.getFlat() );
        customUser.setLogin( customUserDTO.getLogin() );
        customUser.setPassword( customUserDTO.getPassword() );
        customUser.setPassportNum( customUserDTO.getPassportNum() );
        customUser.setIssuedBy( customUserDTO.getIssuedBy() );

        return customUser;
    }

    @Override
    public Client mapFromDto(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setGeneralAdmin( mapFromCustomUserDto( clientDTO.getAdminInfo() ) );
        client.setName( clientDTO.getName() );
        client.setStatus( clientDTO.getStatus() );
        client.setDeleteDate( clientDTO.getDeleteDate() );

        return client;
    }
}
