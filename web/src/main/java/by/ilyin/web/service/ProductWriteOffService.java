package by.ilyin.web.service;

import by.ilyin.web.dto.ProductWriteOffDTO;
import by.ilyin.web.dto.mapper.ProductWriteOffDTOMapper;
import by.ilyin.web.dto.request.UpdateProductWriteOffDTO;
import by.ilyin.web.dto.response.GetProductWriteOffResponseDTO;
import by.ilyin.web.entity.ProductWriteOff;
import by.ilyin.web.feign.ProductWriteOffCoreFeignClient;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductWriteOffService {

    private final ProductWriteOffCoreFeignClient productWriteOffCoreFeignClient;
    private final CustomBindingResultValidator bindingResultValidator;
    private final ProductWriteOffDTOMapper productWriteOffDTOMapper;

    public void createProductWriteOff(ProductWriteOffDTO productWriteOffDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        productWriteOffCoreFeignClient.createProductWriteOff(productWriteOffDTO);
    }

    public void updateProductWriteOff(Long id,
                                      UpdateProductWriteOffDTO updateProductWriteOffDTO,
                                      BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        productWriteOffCoreFeignClient.updateProductWriteOff(id, updateProductWriteOffDTO);
    }

    public void deleteProductWriteOffs(List<Long> productWriteOffIdList) {
        productWriteOffCoreFeignClient.deleteProductWriteOffs(productWriteOffIdList);
    }

    public List<GetProductWriteOffResponseDTO> getProductWriteOffsByInvoiceId(Long invoiceId) {
        return productWriteOffCoreFeignClient.getProductWriteOffsByInvoiceId(invoiceId)
                .stream()
                .map(productWriteOffDTOMapper::mapToDTO)
                .collect(Collectors.toList());
    }

}
