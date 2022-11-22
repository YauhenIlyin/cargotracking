package by.ilyin.web.config;

import by.ilyin.web.security.JwtBlackListManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@RequiredArgsConstructor
public class SchedulerConfig {

    private final JwtBlackListManager jwtBlackListManager;

    @Async
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 3)
    public void getUpToDateBlackList() {
        jwtBlackListManager.updateBlackList();
    }

}
