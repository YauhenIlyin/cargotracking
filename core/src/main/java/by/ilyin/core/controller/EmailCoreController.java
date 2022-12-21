package by.ilyin.core.controller;

import by.ilyin.core.dto.RestoreAccountDTO;
import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailCoreController {

    private final EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<Void> sendMail(@RequestBody SendEmailDTO sendEmailDTO) {
        emailService.sendEmail(sendEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/email/repairing")
    public ResponseEntity<Void> repairEmail(@RequestBody SendEmailDTO sendEmailDTO) {
        emailService.repairEmail(sendEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/restore/{uuid}")
    public ResponseEntity<Void> restoreAccount(@PathVariable("uuid") String uuid,
                                               @RequestBody RestoreAccountDTO restoreAccountDTO) {
        emailService.restoreAccount(uuid, restoreAccountDTO);
        return ResponseEntity
                .ok()
                .build();
    }

}
