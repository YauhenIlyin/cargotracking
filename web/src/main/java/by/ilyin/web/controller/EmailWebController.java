package by.ilyin.web.controller;

import by.ilyin.web.dto.ChangeEmailDTO;
import by.ilyin.web.dto.RestoreAccountDTO;
import by.ilyin.web.dto.SendEmailDTO;
import by.ilyin.web.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api")
public class EmailWebController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody @Valid SendEmailDTO sendEmailDTO) {
        emailService.sendEmail(sendEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/email/repairing")
    public ResponseEntity<Void> repairEmail(@RequestBody @Valid SendEmailDTO sendEmailDTO) {
        emailService.repairEmail(sendEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    //todo password as char[]
    @PutMapping("/restore/{uuid}")
    public ResponseEntity<Void> restoreAccount(@PathVariable("uuid") String uuid,
                                               @RequestBody @Valid RestoreAccountDTO restoreAccountDTO) {
        emailService.restoreAccount(uuid, restoreAccountDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/profile/change-email")
    public ResponseEntity<Void> changeEmail(@RequestBody @Valid ChangeEmailDTO changeEmailDTO) {
        emailService.changeEmail(changeEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/profile/confirm-change-email/{uuid}")
    public ResponseEntity<Void> confirmEmail(@PathVariable("uuid") String uuid) {
        emailService.confirmEmail(uuid);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/template")
    public ResponseEntity<Void> addHappyBirthdayTemplate(@RequestBody String template) {
        emailService.addHappyBirthdayTemplate(template);
        return ResponseEntity
                .ok()
                .build();
    }

}
