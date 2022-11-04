package by.ilyin.core.service;

import by.ilyin.core.dto.response.AppInfoResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppInfoService {
    @Value(value = "${app.info.applicationName}")
    private String applicationName;
    @Value(value = "${app.info.version}")
    private String version;

    public AppInfoResponseDTO getAppInfo() {
        return new AppInfoResponseDTO(applicationName, version);
    }

}
