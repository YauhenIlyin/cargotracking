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

    public void repairEmail(SendEmailDTO sendEmailDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        emailCoreFeignClient.repairEmail(sendEmailDTO);
    }

    public void restoreAccount(String uuid, String password) {
        emailCoreFeignClient.restoreAccount(uuid, password);
    }

}
