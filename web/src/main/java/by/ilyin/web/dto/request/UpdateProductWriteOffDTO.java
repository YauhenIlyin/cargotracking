package by.ilyin.web.dto.request;

import by.ilyin.web.entity.ProductWriteOff;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateProductWriteOffDTO {

    @NotNull(message = "Amount must be not null.")
    private Integer amount;
    private ProductWriteOff.Status status;

}
