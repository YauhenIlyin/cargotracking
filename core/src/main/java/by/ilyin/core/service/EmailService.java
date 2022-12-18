package by.ilyin.core.service;

import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.dto.mapper.EmailDTOMapper;
import by.ilyin.core.util.email.EmailProcessManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailProcessManager emailProcessManager;
    private final EmailDTOMapper emailDTOMapper;

    public void sendEmail(SendEmailDTO sendEmailDTO) {
        emailProcessManager.sendSimpleMail(emailDTOMapper.mapFromDto(sendEmailDTO));
    }

}
