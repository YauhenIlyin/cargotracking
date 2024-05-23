package by.ilyin.core.dto.request;

import lombok.Data;

@Data
public class UpdateProductDTO {

    private Long id;
    private String name;
    private Integer amount;

}
