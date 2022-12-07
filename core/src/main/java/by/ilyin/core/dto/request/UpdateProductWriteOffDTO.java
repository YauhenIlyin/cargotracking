package by.ilyin.core.dto.request;

import by.ilyin.core.entity.ProductWriteOff;
import lombok.Data;

@Data
public class UpdateProductWriteOffDTO {

    private Integer amount;
    private ProductWriteOff.Status status;

}
