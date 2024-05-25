package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.ProductWriteOffDTO;
import by.ilyin.core.entity.ProductWriteOff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:42+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProductWriteOffDTOMapperImpl implements ProductWriteOffDTOMapper {

    @Override
    public ProductWriteOff mapFromDTO(ProductWriteOffDTO productWriteOffDTO) {
        if ( productWriteOffDTO == null ) {
            return null;
        }

        ProductWriteOff productWriteOff = new ProductWriteOff();

        productWriteOff.setAmount( productWriteOffDTO.getAmount() );
        productWriteOff.setStatus( productWriteOffDTO.getStatus() );

        return productWriteOff;
    }
}
