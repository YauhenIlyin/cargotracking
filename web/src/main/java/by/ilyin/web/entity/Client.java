package by.ilyin.web.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class Client {

    private Long id;
    private String name;
    private ClientStatus status;
    private CustomUser generalAdmin;
    private LocalDateTime deleteDate;

    public enum ClientStatus {
        PRIVATE,
        LEGAL
    }

}
