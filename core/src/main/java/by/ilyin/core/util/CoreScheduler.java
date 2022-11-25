package by.ilyin.core.util;

import by.ilyin.core.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@RequiredArgsConstructor
public class CoreScheduler {

    private final EmailService emailService;

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
