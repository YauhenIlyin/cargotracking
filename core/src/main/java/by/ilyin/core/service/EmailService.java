package by.ilyin.core.service;

import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.dto.mapper.EmailDTOMapper;
import by.ilyin.core.util.email.EmailProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailProcessService emailProcessService;
    private final EmailDTOMapper emailDTOMapper;

    public void sendEmail(SendEmailDTO sendEmailDTO) {
        emailProcessService.sendSimpleMail(emailDTOMapper.mapFromDto(sendEmailDTO));
    }

}
