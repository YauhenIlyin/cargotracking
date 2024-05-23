package by.ilyin.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateProductDTO {

    @NotNull(message = "Id must be not null.")
    private Long id;
    @NotNull(message = "Number must be not null.")
    @Size(max = 50, message = "Name must be no more than 50 characters long.")
    private String name;
    @NotNull(message = "Amount must be not null.")
    private Integer amount;

}
