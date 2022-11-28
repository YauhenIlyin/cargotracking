package by.ilyin.core.controller;

import by.ilyin.core.dto.response.AppInfoResponseDTO;
import by.ilyin.core.service.AppInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppCoreController {

    private final AppInfoService appInfoService;

    @GetMapping("/about")
    public AppInfoResponseDTO getAppInfo() {
        return appInfoService.getAppInfo();
    }

}
