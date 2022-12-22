package by.ilyin.core.service;

import by.ilyin.core.dto.RestoreAccountDTO;
import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.dto.mapper.EmailDTOMapper;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.uuid.CustomUUID;
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
    public void repairEmail(SendEmailDTO sendEmailDTO) {
        CustomUser customUser = customUserRepository.findByEmail(sendEmailDTO.getRecipient()).orElseThrow();
        UUID uuid = UUID.randomUUID();
        customUUIDRepository.save(
                new CustomUUID(customUser.getId(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(1),
                        uuid.toString(),
                        CustomUUID.Destination.RESTORE_ACCOUNT));
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
    public void restoreAccount(String uuid, RestoreAccountDTO restoreAccountDTO) {
        CustomUUID customUUID = customUUIDRepository.findByUuidValueAndExpiredDateAfter(uuid, LocalDateTime.now())
                .orElseThrow(() -> new ResourceNotFoundException("Incorrect UUID."));
        CustomUser customUser = customUserRepository.findById(customUUID.getUserId())
                .orElseThrow(() -> new RuntimeException("Internal server error. Account not found."));
        customUser.setPassword(restoreAccountDTO.getPassword());
        customUserRepository.save(customUser);
    }

}
