package by.ilyin.web.service.feign;

import by.ilyin.web.entity.dto.AppInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "appCoreFeignClient", url = "${feign.client.core.url}")
public interface AppInfoFeignClient {

    @GetMapping("/api/about")
    AppInfoDTO getAboutInfo();

}
