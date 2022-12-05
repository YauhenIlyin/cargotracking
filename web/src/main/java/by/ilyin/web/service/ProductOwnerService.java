package by.ilyin.web.service;

import by.ilyin.web.dto.ProductOwnerDTO;
import by.ilyin.web.dto.mapper.ProductOwnerDTOMapper;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateProductOwnerDTO;
import by.ilyin.web.dto.response.CreateProductOwnerResponseDTO;
import by.ilyin.web.dto.response.GetProductOwnerByIdResponseDTO;
import by.ilyin.web.dto.response.GetProductOwnersResponseDTO;
import by.ilyin.web.entity.ProductOwner;
import by.ilyin.web.feign.ProductOwnersCoreFeignClient;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOwnerService {

    private final ProductOwnersCoreFeignClient productOwnersCoreFeignClient;
    private final CustomBindingResultValidator bindingResultValidator;
    private final ProductOwnerDTOMapper productOwnerDTOMapper;

    public CreateProductOwnerResponseDTO createProductOwner(ProductOwnerDTO productOwnerDTO,
                                                            BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        return productOwnersCoreFeignClient.createProductOwner(productOwnerDTO);
    }

    public void updateProductOwner(Long productOwnerId,
                                   UpdateProductOwnerDTO updateProductOwnerDTO,
                                   BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        productOwnersCoreFeignClient.updateProductOwner(productOwnerId, updateProductOwnerDTO);
    }

    public void deleteProductOwner(List<Long> productOwnerIdList) {
        productOwnersCoreFeignClient.deleteProductOwner(productOwnerIdList);
    }

    public GetProductOwnerByIdResponseDTO getProductOwnerById(Long productOwnerId) {
        return productOwnerDTOMapper
                .mapToDto(productOwnersCoreFeignClient.getProductOwnerById(productOwnerId));
    }

    public GetProductOwnersResponseDTO getProductOwners(String name,
                                                        Integer pageSize,
                                                        Integer pageNumber) {
        PageDTO<ProductOwner> pageDTO = productOwnersCoreFeignClient
                .getProductOwners(name, pageSize, pageNumber);
        return new GetProductOwnersResponseDTO(
                pageDTO.getContent()
                        .stream()
                        .map(productOwnerDTOMapper::mapToDto)
                        .collect(Collectors.toList()),
                pageDTO.getTotalElements()
        );
    }

}
