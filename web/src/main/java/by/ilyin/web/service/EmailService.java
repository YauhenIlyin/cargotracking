package by.ilyin.web.service;

import by.ilyin.web.dto.SendEmailDTO;
import by.ilyin.web.feign.EmailCoreFeignClient;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailCoreFeignClient emailCoreFeignClient;
    private final CustomBindingResultValidator bindingResultValidator;

    public void sendEmail(SendEmailDTO sendEmailDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        emailCoreFeignClient.sendEmail(sendEmailDTO);
    }

}
