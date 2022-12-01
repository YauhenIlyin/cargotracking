package by.ilyin.web.dto.request;

import by.ilyin.web.dto.ProductDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UpdateInvoiceDTO {

    @NotNull(message = "Number must be not null.")
    @Size(max = 20, message = "Number must be no more than 20 characters long.")
    private String number;
    @NotNull(message = "StorageId must be not null.")
    private Long storageId;
    @NotNull(message = "ProductOwnerId must be not null.")
    private Long productOwnerId;
    @NotNull(message = "DriverId must be not null.")
    private Long driverId;
    @NotNull
    private List<UpdateProductDTO> products;

}
