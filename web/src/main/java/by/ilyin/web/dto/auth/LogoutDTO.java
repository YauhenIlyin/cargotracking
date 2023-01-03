package by.ilyin.web.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LogoutDTO {

    @NotNull(message = "userId cannot be null")
    private Long userId;

}
