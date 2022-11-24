package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ChangeEmailDTO;
import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.util.email.EmailDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailDTOMapper {

    EmailDetails mapFromDto(SendEmailDTO sendEmailDTO);

    @Mapping(target = "attachment", ignore = true)
    EmailDetails mapFromDto(ChangeEmailDTO changeEmailDTO);

}

