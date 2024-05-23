package by.ilyin.web.dto.response;

import by.ilyin.web.entity.Client;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetClientByIdResponseDTO {

    private Long id;
    private String name;
    private Client.ClientStatus status;
    private LocalDateTime deleteDate;

}
