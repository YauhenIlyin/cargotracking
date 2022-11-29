package by.ilyin.core.config;

import by.ilyin.core.service.EmailService;
import by.ilyin.core.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@RequiredArgsConstructor
public class SchedulerConfig {

    private final AuthService authService;
    private final EmailService emailService;

    @Async
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 3)
    public void cleanExpiredTokens() {
        authService.cleanUpExpiredJWT(LocalDateTime.now());
    }

    //todo rebuild to template from frontend ???
    //delete ?
    @Async
    @Scheduled(cron = "0 0 1 * * *")
    public void happyBirthDay() {
        emailService.happyBirthday(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1));
    }

}
