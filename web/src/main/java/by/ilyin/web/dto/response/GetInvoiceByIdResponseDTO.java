package by.ilyin.web.dto.response;

import by.ilyin.web.entity.Invoice;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetInvoiceByIdResponseDTO {

    private Long id;
    private String number;
    private LocalDate creationDate;
    private LocalDate verifiedDate;
    private Invoice.Status status;
    private Long storageId;
    private Long productOwnerId;
    private Long driverId;

}
