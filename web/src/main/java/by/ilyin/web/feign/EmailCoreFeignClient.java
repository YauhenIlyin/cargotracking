package by.ilyin.web.feign;

import by.ilyin.web.dto.ChangeEmailDTO;
import by.ilyin.web.dto.RestoreAccountDTO;
import by.ilyin.web.dto.SendEmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "emailCoreFeignClient", url = "${feign.client.core.url}")
public interface EmailCoreFeignClient {

    @PostMapping(value = "/api/email", consumes = "application/json")
    void sendEmail(SendEmailDTO sendEmailDTO);

    @PostMapping(value = "/api/email/repairing", consumes = "application/json")
    void repairEmail(SendEmailDTO sendEmailDTO);

    @PutMapping(value = "api/restore/{uuid}", consumes = "application/json")
    void restoreAccount(@PathVariable("uuid") String uuid,
                        @RequestBody RestoreAccountDTO restoreAccountDTO);

    @PostMapping(value = "/api/profile/change-email", consumes = "application/json")
    void changeEmail(@RequestParam("userId") Long userId,
                     ChangeEmailDTO changeEmailDTO);

    @GetMapping(value = "/api/profile/confirm-change-email/{uuid}", consumes = "application/json")
    void confirmEmail(@RequestParam("userId") Long userId,
                      @PathVariable("uuid") String uuid);

    @PostMapping(value = "/api/template")
    void addHappyBirthdayTemplate(@RequestBody String template,
                                  @RequestParam Long clientId);

}
