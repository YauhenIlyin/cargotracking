package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetProductWriteOffResponseDTO;
import by.ilyin.web.entity.ProductWriteOff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductWriteOffDTOMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "name")
    GetProductWriteOffResponseDTO mapToDTO(ProductWriteOff productWriteOff);

}
