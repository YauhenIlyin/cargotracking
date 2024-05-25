package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.CustomUserDTO;
import by.ilyin.web.dto.UserProfileDTO;
import by.ilyin.web.entity.Client;
import by.ilyin.web.entity.CustomUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CustomUserDTOMapperImpl implements CustomUserDTOMapper {

    @Override
    public CustomUserDTO mapToDto(CustomUser customUser) {
        if ( customUser == null ) {
            return null;
        }

        CustomUserDTO customUserDTO = new CustomUserDTO();

        customUserDTO.setClientId( customUserClientId( customUser ) );
        customUserDTO.setName( customUser.getName() );
        customUserDTO.setSurname( customUser.getSurname() );
        customUserDTO.setPatronymic( customUser.getPatronymic() );
        customUserDTO.setBornDate( customUser.getBornDate() );
        customUserDTO.setEmail( customUser.getEmail() );
        customUserDTO.setTown( customUser.getTown() );
        customUserDTO.setStreet( customUser.getStreet() );
        customUserDTO.setHouse( customUser.getHouse() );
        customUserDTO.setFlat( customUser.getFlat() );
        customUserDTO.setLogin( customUser.getLogin() );
        customUserDTO.setPassword( customUser.getPassword() );
        customUserDTO.setPassportNum( customUser.getPassportNum() );
        customUserDTO.setIssuedBy( customUser.getIssuedBy() );

        return customUserDTO;
    }

    @Override
    public UserProfileDTO mapToProfileDto(CustomUser customUser) {
        if ( customUser == null ) {
            return null;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();

        userProfileDTO.setId( customUser.getId() );
        userProfileDTO.setName( customUser.getName() );
        userProfileDTO.setSurname( customUser.getSurname() );
        userProfileDTO.setPatronymic( customUser.getPatronymic() );
        userProfileDTO.setBornDate( customUser.getBornDate() );
        userProfileDTO.setTown( customUser.getTown() );
        userProfileDTO.setStreet( customUser.getStreet() );
        userProfileDTO.setHouse( customUser.getHouse() );
        userProfileDTO.setFlat( customUser.getFlat() );
        userProfileDTO.setPassportNum( customUser.getPassportNum() );
        userProfileDTO.setLogin( customUser.getLogin() );
        userProfileDTO.setEmail( customUser.getEmail() );

        return userProfileDTO;
    }

    @Override
    public CustomUser mapFromProfileDto(UserProfileDTO userProfileDTO) {
        if ( userProfileDTO == null ) {
            return null;
        }

        CustomUser customUser = new CustomUser();

        customUser.setId( userProfileDTO.getId() );
        customUser.setName( userProfileDTO.getName() );
        customUser.setSurname( userProfileDTO.getSurname() );
        customUser.setPatronymic( userProfileDTO.getPatronymic() );
        customUser.setBornDate( userProfileDTO.getBornDate() );
        customUser.setEmail( userProfileDTO.getEmail() );
        customUser.setTown( userProfileDTO.getTown() );
        customUser.setStreet( userProfileDTO.getStreet() );
        customUser.setHouse( userProfileDTO.getHouse() );
        customUser.setFlat( userProfileDTO.getFlat() );
        customUser.setLogin( userProfileDTO.getLogin() );
        customUser.setPassportNum( userProfileDTO.getPassportNum() );

        return customUser;
    }

    private Long customUserClientId(CustomUser customUser) {
        if ( customUser == null ) {
            return null;
        }
        Client client = customUser.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
