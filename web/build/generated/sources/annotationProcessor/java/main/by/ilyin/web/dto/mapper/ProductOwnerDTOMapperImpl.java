package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetProductOwnerByIdResponseDTO;
import by.ilyin.web.entity.ProductOwner;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:05+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProductOwnerDTOMapperImpl implements ProductOwnerDTOMapper {

    @Override
    public GetProductOwnerByIdResponseDTO mapToDto(ProductOwner productOwner) {
        if ( productOwner == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String address = null;

        id = productOwner.getId();
        name = productOwner.getName();
        address = productOwner.getAddress();

        GetProductOwnerByIdResponseDTO getProductOwnerByIdResponseDTO = new GetProductOwnerByIdResponseDTO( id, name, address );

        return getProductOwnerByIdResponseDTO;
    }
}
