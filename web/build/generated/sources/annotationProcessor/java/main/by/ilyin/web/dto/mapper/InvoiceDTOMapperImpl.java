package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetInvoiceByIdResponseDTO;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.entity.Invoice;
import by.ilyin.web.entity.ProductOwner;
import by.ilyin.web.entity.Storage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class InvoiceDTOMapperImpl implements InvoiceDTOMapper {

    @Override
    public GetInvoiceByIdResponseDTO mapToDto(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        GetInvoiceByIdResponseDTO getInvoiceByIdResponseDTO = new GetInvoiceByIdResponseDTO();

        getInvoiceByIdResponseDTO.setStorageId( invoiceStorageId( invoice ) );
        getInvoiceByIdResponseDTO.setProductOwnerId( invoiceProductOwnerId( invoice ) );
        getInvoiceByIdResponseDTO.setDriverId( invoiceDriverId( invoice ) );
        getInvoiceByIdResponseDTO.setId( invoice.getId() );
        getInvoiceByIdResponseDTO.setNumber( invoice.getNumber() );
        getInvoiceByIdResponseDTO.setCreationDate( invoice.getCreationDate() );
        getInvoiceByIdResponseDTO.setStatus( invoice.getStatus() );

        return getInvoiceByIdResponseDTO;
    }

    private Long invoiceStorageId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Storage storage = invoice.getStorage();
        if ( storage == null ) {
            return null;
        }
        Long id = storage.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long invoiceProductOwnerId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        ProductOwner productOwner = invoice.getProductOwner();
        if ( productOwner == null ) {
            return null;
        }
        Long id = productOwner.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long invoiceDriverId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        CustomUser driver = invoice.getDriver();
        if ( driver == null ) {
            return null;
        }
        Long id = driver.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
