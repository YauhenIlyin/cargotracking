package by.ilyin.web.service;

import by.ilyin.web.dto.ChangeEmailDTO;
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
    private final UserProfileService userProfileService;

    public void sendEmail(SendEmailDTO sendEmailDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        emailCoreFeignClient.sendEmail(sendEmailDTO);
    }

    public void repairEmail(SendEmailDTO sendEmailDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        emailCoreFeignClient.repairEmail(sendEmailDTO);
    }

    public void restoreAccount(String uuid, String password) {
        emailCoreFeignClient.restoreAccount(uuid, password);
    }

    public void changeEmail(ChangeEmailDTO changeEmailDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        emailCoreFeignClient.changeEmail(userProfileService.getCurrentCustomUser().getId(), changeEmailDTO);
    }

    public void confirmEmail(String uuid) {
        emailCoreFeignClient.confirmEmail(userProfileService.getCurrentCustomUser().getId(), uuid);
    }

}
