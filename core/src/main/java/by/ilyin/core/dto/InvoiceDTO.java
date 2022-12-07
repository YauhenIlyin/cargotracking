package by.ilyin.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDTO {

    private String number;
    private Long storageId;
    private Long productOwnerId;
    private Long creatorId;
    private Long driverId;
    private List<ProductDTO> products;

}
