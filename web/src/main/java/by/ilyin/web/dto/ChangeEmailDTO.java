package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ChangeEmailDTO {

    @NotEmpty
    private String recipient;
    private String subject;
    @NotEmpty
    private String text;
    @NotEmpty
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters")
    private String password;

}
