package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.InvoiceDTO;
import by.ilyin.core.dto.ProductDTO;
import by.ilyin.core.entity.Invoice;
import by.ilyin.core.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:43+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class InvoiceDTOMapperImpl implements InvoiceDTOMapper {

    @Override
    public Invoice mapFromDTO(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setNumber( invoiceDTO.getNumber() );
        invoice.setProducts( productDTOListToProductList( invoiceDTO.getProducts() ) );

        return invoice;
    }

    @Override
    public Product mapFromDTO(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDTO.getName() );
        product.setAmount( productDTO.getAmount() );

        return product;
    }

    protected List<Product> productDTOListToProductList(List<ProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDTO productDTO : list ) {
            list1.add( mapFromDTO( productDTO ) );
        }

        return list1;
    }
}
