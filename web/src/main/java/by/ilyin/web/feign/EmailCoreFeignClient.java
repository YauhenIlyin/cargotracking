package by.ilyin.web.feign;

import by.ilyin.web.dto.SendEmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "emailCoreFeignClient", url = "${feign.client.core.url}")
public interface EmailCoreFeignClient {

    @PostMapping(value = "/api/email", consumes = "application/json")
    void sendEmail(SendEmailDTO sendEmailDTO);

    @PostMapping(value = "/api/email/repairing", consumes = "application/json")
    void repairEmail(SendEmailDTO sendEmailDTO);

    @GetMapping(value = "api/restore/{uuid}", consumes = "application/json")
    void restoreAccount(@PathVariable("uuid") String uuid,
                        @RequestParam("password") String password);

}
