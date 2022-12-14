package by.ilyin.web.feign;

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

}
