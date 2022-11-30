package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ProductOwnerDTO;
import by.ilyin.core.entity.ProductOwner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOwnerDTOMapper {

    ProductOwner mapFromDTO(ProductOwnerDTO productOwnerDTO);

}
