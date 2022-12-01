package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetInvoiceByIdResponseDTO;
import by.ilyin.web.entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceDTOMapper {

    GetInvoiceByIdResponseDTO mapToDto(Invoice invoice);

}
