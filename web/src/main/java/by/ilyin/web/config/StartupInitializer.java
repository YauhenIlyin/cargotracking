package by.ilyin.web.config;

import by.ilyin.web.security.JwtBlackListManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupInitializer {

    private final JwtBlackListManager jwtBlackListManager;

    //todo module launching queue required core -> web
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        jwtBlackListManager.updateBlackList();
    }

}
