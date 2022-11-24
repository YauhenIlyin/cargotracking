package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.util.email.EmailDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailDTOMapper {

    EmailDetails mapFromDto(SendEmailDTO sendEmailDTO);

}

