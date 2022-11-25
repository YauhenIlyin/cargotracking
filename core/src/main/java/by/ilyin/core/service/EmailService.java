package by.ilyin.core.service;

import by.ilyin.core.dto.ChangeEmailDTO;
import by.ilyin.core.dto.RestoreAccountDTO;
import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.dto.mapper.EmailDTOMapper;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.uuid.CustomUUID;
import by.ilyin.core.exception.http.client.IncorrectValueFormatException;
import by.ilyin.core.exception.http.client.ResourceAlreadyExists;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.CustomUUIDRepository;
import by.ilyin.core.repository.CustomUserRepository;
import by.ilyin.core.util.email.EmailDetails;
import by.ilyin.core.util.email.EmailProcessManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailProcessManager emailProcessManager;
    private final EmailDTOMapper emailDTOMapper;
    private final CustomUserRepository customUserRepository;
    private final CustomUUIDRepository customUUIDRepository;
    @Value("${external.web.server.address}")
    private String webAddress;
    @Value("${external.web.server.port}")
    private String webPort;

    public void sendEmail(SendEmailDTO sendEmailDTO) {
        emailProcessManager.sendSimpleMail(emailDTOMapper.mapFromDto(sendEmailDTO));
    }

    @Transactional
    public void restoreAccount(String uuid, RestoreAccountDTO restoreAccountDTO) {
        CustomUUID customUUID = customUUIDRepository.findByUuidValueAndExpiredDateAfter(uuid, LocalDateTime.now())
                .orElseThrow(() -> new ResourceNotFoundException("Incorrect UUID."));
        CustomUser customUser = customUserRepository.findById(customUUID.getUserId())
                .orElseThrow(() -> new RuntimeException("Internal server error. Account not found."));
        customUser.setPassword(restoreAccountDTO.getPassword());
        customUserRepository.save(customUser);
    }

    @Transactional
    public void repairEmail(SendEmailDTO sendEmailDTO) {
        CustomUser customUser = customUserRepository.findByEmail(sendEmailDTO.getRecipient()).orElseThrow();
        UUID uuid = UUID.randomUUID();
        customUUIDRepository.deleteByUserIdAndDestination(customUser.getId(), CustomUUID.Destination.RESTORE_ACCOUNT);
        customUUIDRepository.save(
                new CustomUUID(customUser.getId(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(1),
                        uuid.toString(),
                        CustomUUID.Destination.RESTORE_ACCOUNT,
                        null));
        StringBuilder messageSB = new StringBuilder();
        messageSB.append(sendEmailDTO.getText())
                .append(" ")
                .append("http://") //todo https ?
                .append(webAddress)
                .append(":")
                .append(webPort)
                .append("/api/restore/")
                .append(uuid);
        EmailDetails emailDetails = emailDTOMapper.mapFromDto(sendEmailDTO);
        emailDetails.setText(messageSB.toString());
        emailProcessManager.sendSimpleMail(emailDetails);
    }

    @Transactional
    public void changeEmail(Long userId, ChangeEmailDTO changeEmailDTO) {
        CustomUser customUser = customUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found by id. Internal server error"));
        if (customUserRepository.existsByEmail(changeEmailDTO.getRecipient())) {
            throw new ResourceAlreadyExists("Email " + changeEmailDTO.getRecipient() + " is not free.");
        }
        if (!customUser.getPassword().equals(changeEmailDTO.getPassword())) {
            throw new IncorrectValueFormatException("Incorrect password.");
        }
        String uuidStr = UUID.randomUUID().toString();
        customUUIDRepository.deleteByUserIdAndDestination(customUser.getId(), CustomUUID.Destination.CHANGE_EMAIL);
        customUUIDRepository.save(
                new CustomUUID(userId,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(1),
                        uuidStr,
                        CustomUUID.Destination.CHANGE_EMAIL,
                        changeEmailDTO.getRecipient()));
        StringBuilder messageSB = new StringBuilder();
        messageSB.append(changeEmailDTO.getText())
                .append(" ")
                .append("http://") //todo https ?
                .append(webAddress)
                .append(":")
                .append(webPort)
                .append("/api/profile/confirm-change-email/")
                .append(uuidStr);
        EmailDetails emailDetails = emailDTOMapper.mapFromDto(changeEmailDTO);
        emailDetails.setText(messageSB.toString());
        emailProcessService.sendSimpleMail(emailDetails);
    }

    public void confirmEmail(Long userId, String uuid) {
        CustomUUID customUUID = customUUIDRepository.findByUuidValueAndExpiredDateAfter(uuid, LocalDateTime.now())
                .orElseThrow(() -> new ResourceNotFoundException("Your request has expired"));
        CustomUser customUser = customUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found by id. Internal server error"));
        customUser.setEmail(customUUID.getEmail());
        customUserRepository.save(customUser);
    }

}
