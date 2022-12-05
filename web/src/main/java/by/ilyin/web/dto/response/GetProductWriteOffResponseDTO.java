package by.ilyin.web.dto.response;

import by.ilyin.web.entity.ProductWriteOff;
import lombok.Data;

@Data
public class GetProductWriteOffResponseDTO {

    private Long id;
    private Long productId;
    private String name;
    private Integer amount;
    private ProductWriteOff.Status status;

}
