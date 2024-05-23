package by.ilyin.web.dto.request;

import by.ilyin.web.entity.Client;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateClientDTO {

    @Size(max = 30, message = "Name must be no more than 20 characters long.")
    private String name;
    @NotNull(message = "Client status must not be null.")
    private Client.ClientStatus status;

}
