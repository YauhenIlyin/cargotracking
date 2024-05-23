package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.InvoiceDTO;
import by.ilyin.core.dto.ProductDTO;
import by.ilyin.core.entity.Invoice;
import by.ilyin.core.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceDTOMapper {

    Invoice mapFromDTO(InvoiceDTO invoiceDTO);

    Product mapFromDTO(ProductDTO productDTO);


}
