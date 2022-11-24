package by.ilyin.core.dto;

import lombok.Data;

@Data
public class SendEmailDTO {

    private String recipient;
    private String subject;
    private String text;

}
