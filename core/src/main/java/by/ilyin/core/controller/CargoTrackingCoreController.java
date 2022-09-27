package by.ilyin.core.controller;

import by.ilyin.core.entity.dto.AppInfoDTO;
import by.ilyin.core.service.AppInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CargoTrackingCoreController {

    private final AppInfoService appInfoService;

    @GetMapping("/about")
    public AppInfoDTO getAppInfo() {
        AppInfoDTO appInfoDTO;
        appInfoDTO = appInfoService.getAppInfo();
        return appInfoDTO;
    }

}
