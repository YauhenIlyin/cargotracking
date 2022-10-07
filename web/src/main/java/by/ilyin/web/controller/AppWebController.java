package by.ilyin.web.controller;

import by.ilyin.web.entity.dto.AppInfoDTO;
import by.ilyin.web.service.AppInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppWebController {

    private final AppInfoService appInfoService;

    @GetMapping("/about")
    public AppInfoDTO getAppInfo() {
        return appInfoService.getAppInfo();
    }

}
