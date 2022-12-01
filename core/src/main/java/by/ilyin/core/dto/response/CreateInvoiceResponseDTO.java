package by.ilyin.core.dto.response;

import lombok.Data;

@Data
public class CreateInvoiceResponseDTO {

    private String location;

    public CreateInvoiceResponseDTO(Long invoiceId) {
        this.location = "/api/invoices/" + invoiceId;
    }

}
