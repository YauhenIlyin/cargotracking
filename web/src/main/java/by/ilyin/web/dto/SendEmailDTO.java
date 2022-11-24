package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SendEmailDTO {

    //todo to any?
    @NotNull(message = "Field email must not be null")
    @Email(message = "String doesn't look like email")
    private String recipient;
    private String subject;
    @NotNull(message = "Field text must not be null")
    @NotEmpty(message = "Field text must not be empty")
    private String text;

}
