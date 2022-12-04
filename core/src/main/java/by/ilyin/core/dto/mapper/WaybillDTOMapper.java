package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.WaybillDTO;
import by.ilyin.core.entity.Waybill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WaybillDTOMapper {

    Waybill mapFromDTO(WaybillDTO waybillDTO);

}
