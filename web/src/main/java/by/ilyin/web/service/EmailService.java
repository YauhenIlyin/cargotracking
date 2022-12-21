package by.ilyin.web.service;

import by.ilyin.web.dto.SendEmailDTO;
import by.ilyin.web.feign.EmailCoreFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailCoreFeignClient emailCoreFeignClient;

    public void sendEmail(SendEmailDTO sendEmailDTO) {
        emailCoreFeignClient.sendEmail(sendEmailDTO);
    }

}
