package by.ilyin.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateStorageRequestDTO {

    @NotNull(message = "Name must be not null.")
    @Size(max = 20, message = "Name must be no more than 20 characters long.")
    private String name;
    @NotNull(message = "Address must be not null.")
    @Size(max = 40, message = "Address must be no more than 40 characters long.")
    private String address;

}
