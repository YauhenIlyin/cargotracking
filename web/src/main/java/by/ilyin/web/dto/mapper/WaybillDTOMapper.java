package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetWaybillResponseDTO;
import by.ilyin.web.entity.Waybill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WaybillDTOMapper {

    @Mapping(source = "invoice.productOwner.name", target = "sender")
    @Mapping(source = "invoice.storage.name", target = "receiver")
    @Mapping(source = "invoice.number", target = "invoiceNumber")
    @Mapping(source = "car.number", target = "carNumber")
    @Mapping(source = "invoice.id", target = "invoiceId")
    @Mapping(source = "car.id", target = "carId")
    GetWaybillResponseDTO mapToDTO(Waybill waybill);

}
