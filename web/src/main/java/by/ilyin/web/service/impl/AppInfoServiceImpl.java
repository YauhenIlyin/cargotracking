package by.ilyin.web.service.impl;

import by.ilyin.web.entity.dto.AppInfoDTO;
import by.ilyin.web.service.AppInfoService;
import by.ilyin.web.service.feign.AppInfoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppInfoServiceImpl implements AppInfoService {

    private final AppInfoFeignClient appInfoFeignClient;

    @Override
    public AppInfoDTO getAppInfo() {
        AppInfoDTO appInfoDTO;
        appInfoDTO = appInfoFeignClient.getAboutInfo();
        return appInfoDTO;
    }

}
