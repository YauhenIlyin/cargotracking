package by.ilyin.core.service;

import by.ilyin.core.dto.ChangeEmailDTO;
import by.ilyin.core.dto.RestoreAccountDTO;
import by.ilyin.core.dto.SendEmailDTO;
import by.ilyin.core.dto.mapper.EmailDTOMapper;
import by.ilyin.core.entity.Client;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.HappyBirthdayTemplate;
import by.ilyin.core.entity.uuid.CustomUUID;
import by.ilyin.core.exception.http.client.ResourceAlreadyExists;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.ClientRepository;
import by.ilyin.core.repository.CustomUUIDRepository;
import by.ilyin.core.repository.CustomUserRepository;
import by.ilyin.core.repository.HappyBirthdayTemplateRepository;
import by.ilyin.core.util.email.EmailDetails;
import by.ilyin.core.util.email.EmailProcessManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private String DEFAULT_HAPPY_BIRTH_SUBJECT = "This beautiful day!";
    private String DEFAULT_HAPPY_BIRTH_TEXT = "HappyBirthday! Cheers, ";
    private final EmailProcessManager emailProcessManager;
    private final EmailDTOMapper emailDTOMapper;
    private final CustomUserRepository customUserRepository;
    private final CustomUUIDRepository customUUIDRepository;
    private final HappyBirthdayTemplateRepository happyBirthdayRepository;
    private final ClientRepository clientRepository;
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
        CustomUser customUser = customUserRepository.findById(userId).orElseThrow();
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
                .append("http://")
                .append(webAddress)
                .append(":")
                .append(webPort)
                .append("/api/profile/confirm-change-email/")
                .append(uuidStr);
        EmailDetails emailDetails = emailDTOMapper.mapFromDto(changeEmailDTO);
        emailDetails.setText(messageSB.toString());
        emailProcessManager.sendSimpleMail(emailDetails);
    }

    @Transactional
    public void confirmEmail(Long userId, String uuid) {
        CustomUUID customUUID = customUUIDRepository.findByUuidValueAndExpiredDateAfter(uuid, LocalDateTime.now())
                .orElseThrow(() -> {
                    customUUIDRepository.deleteByUserIdAndDestination(userId, CustomUUID.Destination.CHANGE_EMAIL);
                    return new ResourceNotFoundException("Your request has expired.");
                });
        if (customUserRepository.existsByEmail(customUUID.getEmail())) {
            throw new ResourceAlreadyExists("The email was taken before you confirmed.");
        }
        CustomUser customUser = customUserRepository.findById(userId).orElseThrow();
        customUser.setEmail(customUUID.getEmail());
        customUserRepository.save(customUser);
    }

    public void addHappyBirthdayTemplate(String template, Long clientId) {
        //todo exists client by id ? throw @ nullpointer in custom valid? 500 ?
        Client client = clientRepository.findById(clientId).orElseThrow();
        HappyBirthdayTemplate happyBirthdayTemplate = new HappyBirthdayTemplate();
        happyBirthdayTemplate.setTemplate(template);
        happyBirthdayTemplate.setClient(client);
        happyBirthdayRepository.save(happyBirthdayTemplate);
    }

    public void happyBirthday(LocalDate fromBornDate, LocalDate toBornDate) {
        customUserRepository.findAllByBornDateAfterAndBornDateBefore(fromBornDate, toBornDate)
                .stream()
                .map(o -> {
                    Optional<HappyBirthdayTemplate> optionalTemplate =
                            happyBirthdayRepository.findByClient_Id(o.getClient().getId());
                    return new EmailDetails(
                            o.getEmail(),
                            DEFAULT_HAPPY_BIRTH_SUBJECT,
                            optionalTemplate.isPresent() ?
                                    optionalTemplate.get().getTemplate() :
                                    DEFAULT_HAPPY_BIRTH_TEXT + o.getName() + "!"
                    );
                })
                .map(e -> {
                            emailProcessManager.sendSimpleMail(e);
                            return null;
                        }
                ).close();
    }

}
