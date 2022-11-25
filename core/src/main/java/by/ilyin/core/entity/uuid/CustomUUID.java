package by.ilyin.core.entity.uuid;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(UUIDCustomId.class)
@Table(name = "custom_uuid")
@Entity
public class CustomUUID {

    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "issued_at_date")
    private LocalDateTime issuedDate;
    @Column(name = "expired_at_date")
    private LocalDateTime expiredDate;
    @Column(name = "uuid")
    private String uuidValue;
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "destination")
    private Destination destination;

    public enum Destination {
        RESTORE_ACCOUNT,
        CHANGE_EMAIL
    }

}
