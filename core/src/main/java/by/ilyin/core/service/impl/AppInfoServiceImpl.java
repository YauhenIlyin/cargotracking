package by.ilyin.core.service.impl;

import by.ilyin.core.entity.dto.AppInfoDTO;
import by.ilyin.core.service.AppInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Value(value = "${app.info.applicationName}")
    private String applicationName;
    @Value(value = "${app.info.version}")
    private String version;

    @Override
    public AppInfoDTO getAppInfo() {
        AppInfoDTO appInfoDTO;
        appInfoDTO = new AppInfoDTO(applicationName, version);
        return appInfoDTO;
    }
}
