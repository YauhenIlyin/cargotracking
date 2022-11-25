package by.ilyin.core.dto;

import lombok.Data;

@Data
public class ChangeEmailDTO {

    private String recipient;
    private String subject;
    private String text;
    private String password;

}
