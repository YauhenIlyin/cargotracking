package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ChangeEmailDTO {

    @NotEmpty(message = "Recipient must not be empty.")
    private String recipient;
    private String subject;
    @NotEmpty(message = "Text must not be empty.")
    private String text;
    @NotNull
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters")
    private String password;

}
