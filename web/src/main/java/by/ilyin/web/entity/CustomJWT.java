package by.ilyin.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomJWT {

    private Long userId;
    private LocalDateTime issuedDate;
    private LocalDateTime expiredDate;
    private String token;
    Boolean isNotBlocked;

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
