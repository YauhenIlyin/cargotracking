package by.ilyin.web.feign;

import by.ilyin.web.dto.response.AppInfoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aboutCoreFeignClient", url = "${feign.client.core.url}")
public interface AppInfoFeignClient {

    @GetMapping("/api/about")
    AppInfoResponseDTO getAboutInfo();

}