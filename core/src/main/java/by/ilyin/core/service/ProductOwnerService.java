package by.ilyin.core.service;

import by.ilyin.core.dto.ProductOwnerDTO;
import by.ilyin.core.dto.mapper.ProductOwnerDTOMapper;
import by.ilyin.core.dto.request.UpdateProductOwnerDTO;
import by.ilyin.core.dto.response.CreateProductOwnerResponseDTO;
<<<<<<< HEAD
import by.ilyin.core.entity.Client;
=======
>>>>>>> dd56a77 (CTB-11 CRUD product owners added)
import by.ilyin.core.entity.ProductOwner;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.ClientRepository;
import by.ilyin.core.repository.ProductOwnerRepository;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductOwnerService {

    private final ClientRepository clientRepository;
    private final ProductOwnerRepository productOwnerRepository;
    private final ProductOwnerDTOMapper productOwnerDTOMapper;
    private final @Qualifier("productOwnerFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateProductOwnerResponseDTO createProductOwner(ProductOwnerDTO productOwnerDTO) {
<<<<<<< HEAD
        Client client = clientRepository.findById(productOwnerDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with id " + productOwnerDTO.getClientId() + " not found."));
        ProductOwner productOwner = productOwnerDTOMapper.mapFromDTO(productOwnerDTO);
        productOwner.setClient(client);
        productOwnerRepository.save(productOwner);
=======
        clientRepository.findById(productOwnerDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with id " + productOwnerDTO.getClientId() + " not found."));
        productOwnerRepository.save(productOwnerDTOMapper.mapFromDTO(productOwnerDTO));
>>>>>>> dd56a77 (CTB-11 CRUD product owners added)
        return new CreateProductOwnerResponseDTO(productOwnerDTO.getAddress());
    }

    @Transactional
    public void updateProductOwner(Long productOwnerId,
                                   UpdateProductOwnerDTO updateProductOwnerDTO) {
        ProductOwner productOwner = productOwnerRepository.findById(productOwnerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product owner with id " + productOwnerId + " not found."));
        productOwner.setName(updateProductOwnerDTO.getName());
        productOwner.setAddress(updateProductOwnerDTO.getAddress());
        productOwnerRepository.save(productOwner);
    }

    @Transactional
    public void deleteProductOwner(List<Long> productOwnerIdList) {
        productOwnerRepository.deleteByIdIsIn(productOwnerIdList);
    }

    public ProductOwner getProductOwnerById(Long productOwnerId) {
        return productOwnerRepository.findById(productOwnerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product owner with id " + productOwnerId + " not found."));
    }

    public Page<ProductOwner> getProductOwners(String name, Pageable pageable) {
        Map<String, Object> filterValues = new HashMap<>();
        filterValues.put("name", name);
        Specification<ProductOwner> productOwnerSpec = takeGetProductOwnersSpecification(filterValues);
        return productOwnerRepository.findAll(productOwnerSpec, pageable);
    }

    private Specification<ProductOwner> takeGetProductOwnersSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<ProductOwner> storageFiltrationBuilder = new FiltrationBuilder<>();
        storageFiltrationBuilder
                .addCriteria(
                        filterValues.get("name") != null,
                        "name",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("name"));
        return storageFiltrationBuilder.build(fieldCriteriaTypes);
    }

}
