package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StorageDTO {

    @NotNull(message = "Name must be not null.")
    @Size(max = 20, message = "Name must be no more than 20 characters long.")
    private String name;
    @NotNull(message = "Address must be not null.")
    @Size(max = 40, message = "Address must be no more than 40 characters long.")
    private String address;
    @NotNull(message = "Client id must be not null.")
    private Long clientId;

}
