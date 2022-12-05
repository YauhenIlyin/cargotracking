package by.ilyin.web.dto;

import by.ilyin.web.entity.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class InvoiceDTO {

    @NotNull(message = "Number must be not null.")
    @Size(max = 20, message = "Number must be no more than 20 characters long.")
    private String number;
    @NotNull(message = "Storage id must be not null.")
    private Long storageId;
    @NotNull(message = "Product owner id must be not null.")
    private Long productOwnerId;
    @NotNull(message = "Creator id must be not null.")
    private Long creatorId;
    @NotNull(message = "Driver id must be not null.")
    private Long driverId;
    private List<Product> products;

}
