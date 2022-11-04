package by.ilyin.web.service;

import by.ilyin.web.dto.response.AppInfoResponseDTO;
import by.ilyin.web.feign.AppInfoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppInfoService {

    private final AppInfoFeignClient appInfoFeignClient;

    public AppInfoResponseDTO getAppInfo() {
        return appInfoFeignClient.getAboutInfo();
    }

}
