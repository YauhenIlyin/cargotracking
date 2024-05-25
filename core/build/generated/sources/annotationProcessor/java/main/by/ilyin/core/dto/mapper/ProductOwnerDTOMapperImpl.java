package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ProductOwnerDTO;
import by.ilyin.core.entity.ProductOwner;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:42+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProductOwnerDTOMapperImpl implements ProductOwnerDTOMapper {

    @Override
    public ProductOwner mapFromDTO(ProductOwnerDTO productOwnerDTO) {
        if ( productOwnerDTO == null ) {
            return null;
        }

        ProductOwner productOwner = new ProductOwner();

        productOwner.setName( productOwnerDTO.getName() );
        productOwner.setAddress( productOwnerDTO.getAddress() );

        return productOwner;
    }
}
