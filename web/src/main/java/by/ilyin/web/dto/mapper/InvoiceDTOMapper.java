package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetInvoiceByIdResponseDTO;
import by.ilyin.web.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceDTOMapper {

    @Mapping(source = "storage.id", target = "storageId")
    @Mapping(source = "productOwner.id", target = "productOwnerId")
    @Mapping(source = "driver.id", target = "driverId")
    GetInvoiceByIdResponseDTO mapToDto(Invoice invoice);

}
