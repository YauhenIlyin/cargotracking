package by.ilyin.web.dto;

import by.ilyin.web.entity.ProductWriteOff;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductWriteOffDTO {

    @NotNull(message = "ProductId must be not null.")
    private Long productId;
    private Long creatorId;
    @NotNull(message = "Amount must be not null.")
    private Integer amount;
    private ProductWriteOff.Status status;

}
