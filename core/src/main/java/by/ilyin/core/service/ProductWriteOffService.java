package by.ilyin.core.service;

import by.ilyin.core.dto.ProductWriteOffDTO;
import by.ilyin.core.dto.mapper.ProductWriteOffDTOMapper;
import by.ilyin.core.dto.request.UpdateProductWriteOffDTO;
import by.ilyin.core.entity.BaseEntity;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.Product;
import by.ilyin.core.entity.ProductWriteOff;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.CustomUserRepository;
import by.ilyin.core.repository.InvoiceRepository;
import by.ilyin.core.repository.ProductRepository;
import by.ilyin.core.repository.ProductWriteOffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductWriteOffService {

    private final ProductWriteOffRepository productWriteOffRepository;
    private final ProductWriteOffDTOMapper productWriteOffDTOMapper;
    private final ProductRepository productRepository;
    private final CustomUserRepository customUserRepository;
    private final InvoiceRepository invoiceRepository;

    public void createProductWriteOff(ProductWriteOffDTO productWriteOffDTO) {
        Product product = (Product) validateAndGetResourceById(productRepository, productWriteOffDTO.getProductId(), "Product");
        CustomUser creator = null;
        if (productWriteOffDTO.getCreatorId() != null) {
            creator = (CustomUser) validateAndGetResourceById(customUserRepository, productWriteOffDTO.getCreatorId(), "Creator");
        }
        ProductWriteOff productWriteOff = productWriteOffDTOMapper.mapFromDTO(productWriteOffDTO);
        productWriteOff.setProduct(product);
        productWriteOff.setCreator(creator);
        productWriteOffRepository.save(productWriteOff);
    }

    public void updateProductWriteOff(Long id, UpdateProductWriteOffDTO updateProductWriteOffDTO) {
        ProductWriteOff productWriteOff = (ProductWriteOff) validateAndGetResourceById(
                productWriteOffRepository, id, "ProductWriteOff");
        productWriteOff.setAmount(updateProductWriteOffDTO.getAmount());
        productWriteOff.setStatus(updateProductWriteOffDTO.getStatus());
        productWriteOffRepository.save(productWriteOff);
    }

    public void deleteProductWriteOffs(List<Long> productWriteOffIdList) {
        productWriteOffRepository.deleteByIdIsIn(productWriteOffIdList);
    }

    public List<ProductWriteOff> getProductWriteOffsByInvoiceId(Long invoiceId) {
        validateAndGetResourceById(invoiceRepository, invoiceId, "Invoice");
        return productWriteOffRepository.findAllByProduct_Invoice_Id(invoiceId);
    }

    private BaseEntity validateAndGetResourceById(JpaRepository<? extends BaseEntity, Long> repository, Long id, String subject) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(subject + " with id " + id + " not found."));
    }

}
