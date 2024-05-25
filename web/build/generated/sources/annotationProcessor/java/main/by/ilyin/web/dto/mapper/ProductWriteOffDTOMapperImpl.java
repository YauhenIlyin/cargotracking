package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetProductWriteOffResponseDTO;
import by.ilyin.web.entity.Product;
import by.ilyin.web.entity.ProductWriteOff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:05+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProductWriteOffDTOMapperImpl implements ProductWriteOffDTOMapper {

    @Override
    public GetProductWriteOffResponseDTO mapToDTO(ProductWriteOff productWriteOff) {
        if ( productWriteOff == null ) {
            return null;
        }

        GetProductWriteOffResponseDTO getProductWriteOffResponseDTO = new GetProductWriteOffResponseDTO();

        getProductWriteOffResponseDTO.setProductId( productWriteOffProductId( productWriteOff ) );
        getProductWriteOffResponseDTO.setName( productWriteOffProductName( productWriteOff ) );
        getProductWriteOffResponseDTO.setId( productWriteOff.getId() );
        getProductWriteOffResponseDTO.setAmount( productWriteOff.getAmount() );
        getProductWriteOffResponseDTO.setStatus( productWriteOff.getStatus() );

        return getProductWriteOffResponseDTO;
    }

    private Long productWriteOffProductId(ProductWriteOff productWriteOff) {
        if ( productWriteOff == null ) {
            return null;
        }
        Product product = productWriteOff.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productWriteOffProductName(ProductWriteOff productWriteOff) {
        if ( productWriteOff == null ) {
            return null;
        }
        Product product = productWriteOff.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
