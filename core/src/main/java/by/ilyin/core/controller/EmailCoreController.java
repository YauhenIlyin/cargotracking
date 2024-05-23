package by.ilyin.core.controller;

<<<<<<< HEAD
import by.ilyin.core.dto.ChangeEmailDTO;
=======
>>>>>>> d3f0bb6 (CTB-16 code cleaned, restore-account request changed GET -> PUT restore method)
import by.ilyin.core.dto.RestoreAccountDTO;
import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.service.EmailService;
import by.ilyin.core.util.validation.custom.ConsistentChangeEmailParameters;
import by.ilyin.core.util.validation.custom.ValidEmailRepairData;
import by.ilyin.core.util.validation.custom.ValidIdByUserExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
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
    public ResponseEntity<Void> repairEmail(@RequestBody @ValidEmailRepairData SendEmailDTO sendEmailDTO) {
        emailService.repairEmail(sendEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/restore/{uuid}")
    public ResponseEntity<Void> restoreAccount(@PathVariable("uuid") String uuid,
                                               @RequestBody RestoreAccountDTO restoreAccountDTO) {
        emailService.restoreAccount(uuid, restoreAccountDTO);
<<<<<<< HEAD
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/profile/change-email")
    @ConsistentChangeEmailParameters
    public ResponseEntity<Void> changeEmail(@RequestParam("userId") Long userId,
                                            @RequestBody ChangeEmailDTO changeEmailDTO) {
        emailService.changeEmail(userId, changeEmailDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/profile/confirm-change-email/{uuid}")
    public ResponseEntity<Void> confirmEmail(@RequestParam("userId") @ValidIdByUserExists Long userId,
                                             @PathVariable("uuid") String uuid) {
        emailService.confirmEmail(userId, uuid);
=======
>>>>>>> d3f0bb6 (CTB-16 code cleaned, restore-account request changed GET -> PUT restore method)
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/template")
    public ResponseEntity<Void> addHappyBirthdayTemplate(@RequestBody String template,
                                                         @RequestParam Long clientId) {
        emailService.addHappyBirthdayTemplate(template, clientId);
        return ResponseEntity
                .ok()
                .build();
    }

}
