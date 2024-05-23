package by.ilyin.core.dto;

import by.ilyin.core.entity.ProductWriteOff;
import lombok.Data;

@Data
public class ProductWriteOffDTO {

    private Long productId;
    private Long creatorId;
    private Integer amount;
    private ProductWriteOff.Status status;

}
