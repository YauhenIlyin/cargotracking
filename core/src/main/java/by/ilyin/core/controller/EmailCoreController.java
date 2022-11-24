package by.ilyin.core.controller;

import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailCoreController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody SendEmailDTO sendEmailDTO) {
        emailService.sendEmail(sendEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

}
