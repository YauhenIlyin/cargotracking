package by.ilyin.core.dto;

import by.ilyin.core.entity.Client;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientDTO {

    private String name;
    private Client.ClientStatus status;
    private CustomUserDTO adminInfo;
    private LocalDateTime deleteDate;

}
