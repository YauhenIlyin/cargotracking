package by.ilyin.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "user_tokens")
public class CustomJWT {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "issued_at_date")
    private LocalDateTime issuedDate;
    @Column(name = "expired_at_date")
    private LocalDateTime expiredDate;
    @Id
    @Column(name = "token")
    private String token;
    @Column(name = "is_not_blocked")
    Boolean isNotBlocked = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomJWT customJwt = (CustomJWT) o;
        return token.equals(customJwt.token);
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }

}
