package by.ilyin.web.feign;

import by.ilyin.web.dto.SendEmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "emailCoreFeignClient", url = "${feign.client.core.url}")
public interface EmailCoreFeignClient {

    @PostMapping(value = "/api/email", consumes = "application/json")
    void sendEmail(SendEmailDTO sendEmailDTO);

}
