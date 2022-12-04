package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetProductOwnerByIdResponseDTO;
import by.ilyin.web.entity.ProductOwner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOwnerDTOMapper {

    GetProductOwnerByIdResponseDTO mapToDto(ProductOwner productOwner);

}
