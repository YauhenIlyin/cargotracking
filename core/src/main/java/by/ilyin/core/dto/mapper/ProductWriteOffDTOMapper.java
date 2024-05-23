package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ProductWriteOffDTO;
import by.ilyin.core.entity.ProductWriteOff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWriteOffDTOMapper {

    ProductWriteOff mapFromDTO(ProductWriteOffDTO productWriteOffDTO);

}
