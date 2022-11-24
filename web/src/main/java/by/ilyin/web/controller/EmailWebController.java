package by.ilyin.web.controller;

import by.ilyin.web.dto.SendEmailDTO;
import by.ilyin.web.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailWebController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody @Valid SendEmailDTO sendEmailDTO,
                                         BindingResult bindingResult) {
        emailService.sendEmail(sendEmailDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

}
