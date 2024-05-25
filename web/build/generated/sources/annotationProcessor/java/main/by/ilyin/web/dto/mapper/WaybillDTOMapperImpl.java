package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetWaybillResponseDTO;
import by.ilyin.web.entity.Car;
import by.ilyin.web.entity.Invoice;
import by.ilyin.web.entity.ProductOwner;
import by.ilyin.web.entity.Storage;
import by.ilyin.web.entity.Waybill;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:05+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class WaybillDTOMapperImpl implements WaybillDTOMapper {

    @Override
    public GetWaybillResponseDTO mapToDTO(Waybill waybill) {
        if ( waybill == null ) {
            return null;
        }

        GetWaybillResponseDTO getWaybillResponseDTO = new GetWaybillResponseDTO();

        getWaybillResponseDTO.setSender( waybillInvoiceProductOwnerName( waybill ) );
        getWaybillResponseDTO.setReceiver( waybillInvoiceStorageName( waybill ) );
        getWaybillResponseDTO.setInvoiceNumber( waybillInvoiceNumber( waybill ) );
        getWaybillResponseDTO.setCarNumber( waybillCarNumber( waybill ) );
        getWaybillResponseDTO.setInvoiceId( waybillInvoiceId( waybill ) );
        getWaybillResponseDTO.setCarId( waybillCarId( waybill ) );
        getWaybillResponseDTO.setId( waybill.getId() );
        getWaybillResponseDTO.setStartDate( waybill.getStartDate() );
        getWaybillResponseDTO.setStatus( waybill.getStatus() );

        return getWaybillResponseDTO;
    }

    private String waybillInvoiceProductOwnerName(Waybill waybill) {
        if ( waybill == null ) {
            return null;
        }
        Invoice invoice = waybill.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        ProductOwner productOwner = invoice.getProductOwner();
        if ( productOwner == null ) {
            return null;
        }
        String name = productOwner.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String waybillInvoiceStorageName(Waybill waybill) {
        if ( waybill == null ) {
            return null;
        }
        Invoice invoice = waybill.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        Storage storage = invoice.getStorage();
        if ( storage == null ) {
            return null;
        }
        String name = storage.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String waybillInvoiceNumber(Waybill waybill) {
        if ( waybill == null ) {
            return null;
        }
        Invoice invoice = waybill.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String number = invoice.getNumber();
        if ( number == null ) {
            return null;
        }
        return number;
    }

    private String waybillCarNumber(Waybill waybill) {
        if ( waybill == null ) {
            return null;
        }
        Car car = waybill.getCar();
        if ( car == null ) {
            return null;
        }
        String number = car.getNumber();
        if ( number == null ) {
            return null;
        }
        return number;
    }

    private Long waybillInvoiceId(Waybill waybill) {
        if ( waybill == null ) {
            return null;
        }
        Invoice invoice = waybill.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        Long id = invoice.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long waybillCarId(Waybill waybill) {
        if ( waybill == null ) {
            return null;
        }
        Car car = waybill.getCar();
        if ( car == null ) {
            return null;
        }
        Long id = car.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
