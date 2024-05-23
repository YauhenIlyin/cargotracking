package by.ilyin.web.dto;

import by.ilyin.web.entity.Client;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ClientDTO {

    @Size(max = 20, message = "Name must be no more than 20 characters long.")
    private String name;
    @NotNull(message = "Client status must not be null.")
    private Client.ClientStatus status;
    @NotNull
    private CustomUserDTO adminInfo;
    private LocalDateTime deleteDate;

}
