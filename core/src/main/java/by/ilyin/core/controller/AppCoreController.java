package by.ilyin.core.controller;

import by.ilyin.core.entity.dto.AppInfoDTO;
import by.ilyin.core.service.AppInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppCoreController {

    private final AppInfoService appInfoService;

    @GetMapping("/about")
    public AppInfoDTO getAppInfo() {
        return appInfoService.getAppInfo();
    }

}
