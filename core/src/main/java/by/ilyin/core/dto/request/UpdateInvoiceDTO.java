package by.ilyin.core.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateInvoiceDTO {

    private String number;
    private Long storageId;
    private Long productOwnerId;
    private Long driverId;
    private List<UpdateProductDTO> products;

}
