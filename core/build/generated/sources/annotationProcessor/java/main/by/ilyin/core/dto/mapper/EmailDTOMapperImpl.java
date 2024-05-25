package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ChangeEmailDTO;
import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.util.email.EmailDetails;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:33:18+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class EmailDTOMapperImpl implements EmailDTOMapper {

    @Override
    public EmailDetails mapFromDto(SendEmailDTO sendEmailDTO) {
        if ( sendEmailDTO == null ) {
            return null;
        }

        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient( sendEmailDTO.getRecipient() );
        emailDetails.setSubject( sendEmailDTO.getSubject() );
        emailDetails.setText( sendEmailDTO.getText() );

        return emailDetails;
    }

    @Override
    public EmailDetails mapFromDto(ChangeEmailDTO changeEmailDTO) {
        if ( changeEmailDTO == null ) {
            return null;
        }

        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient( changeEmailDTO.getRecipient() );
        emailDetails.setSubject( changeEmailDTO.getSubject() );
        emailDetails.setText( changeEmailDTO.getText() );

        return emailDetails;
    }
}
