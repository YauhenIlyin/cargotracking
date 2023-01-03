package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RestoreAccountDTO {

    @NotNull(message = "Password must not be null")
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters")
    private String password;

}
