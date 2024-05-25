package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.CustomUserDTO;
import by.ilyin.core.entity.CustomUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:42+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CustomUserDTOMapperImpl implements CustomUserDTOMapper {

    @Override
    public CustomUser mapFromDto(CustomUserDTO customUserDTO) {
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
    public CustomUserDTO mapToDto(CustomUser customUser) {
        if ( customUser == null ) {
            return null;
        }

        CustomUserDTO customUserDTO = new CustomUserDTO();

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
}
