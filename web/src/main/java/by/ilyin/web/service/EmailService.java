package by.ilyin.web.service;

import by.ilyin.web.dto.ChangeEmailDTO;
import by.ilyin.web.dto.RestoreAccountDTO;
import by.ilyin.web.dto.SendEmailDTO;
import by.ilyin.web.feign.EmailCoreFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailCoreFeignClient emailCoreFeignClient;
    private final UserProfileService userProfileService;

    public void sendEmail(SendEmailDTO sendEmailDTO) {
        emailCoreFeignClient.sendEmail(sendEmailDTO);
    }

    public void repairEmail(SendEmailDTO sendEmailDTO) {
        emailCoreFeignClient.repairEmail(sendEmailDTO);
    }

    public void restoreAccount(String uuid, RestoreAccountDTO restoreAccountDTO) {
        emailCoreFeignClient.restoreAccount(uuid, restoreAccountDTO);
    }

    public void changeEmail(ChangeEmailDTO changeEmailDTO) {
        emailCoreFeignClient.changeEmail(userProfileService.getCurrentCustomUser().getId(), changeEmailDTO);
    }

    public void confirmEmail(String uuid) {
        emailCoreFeignClient.confirmEmail(userProfileService.getCurrentCustomUser().getId(), uuid);
    }

}
