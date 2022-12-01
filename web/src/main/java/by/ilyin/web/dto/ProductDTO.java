package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductDTO {

    @NotNull(message = "Name must be not null.")
    @Size(max = 50, message = "Name must be no more than 50 characters long.")
    private String name;
    @NotNull(message = "Amount must be not null.")
    private Integer amount;

}
