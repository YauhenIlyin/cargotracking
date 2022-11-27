package by.ilyin.core.dto.request;

import by.ilyin.core.entity.Client;
import lombok.Data;

@Data
public class UpdateClientDTO {

    private String name;
    private Client.ClientStatus status;

}
