package by.ilyin.web.controller;

import by.ilyin.web.dto.ProductOwnerDTO;
import by.ilyin.web.dto.request.UpdateProductOwnerDTO;
import by.ilyin.web.dto.response.CreateProductOwnerResponseDTO;
import by.ilyin.web.dto.response.GetProductOwnerByIdResponseDTO;
import by.ilyin.web.dto.response.GetProductOwnersResponseDTO;
import by.ilyin.web.service.ProductOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-owners")
public class ProductOwnerWebController {

    private final ProductOwnerService productOwnerService;

    @PostMapping
    public CreateProductOwnerResponseDTO createProductOwner(@RequestBody @Valid ProductOwnerDTO productOwnerDTO,
                                                            BindingResult bindingResult) {
        return productOwnerService.createProductOwner(productOwnerDTO, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductOwner(@PathVariable Long id,
                                                   @RequestBody UpdateProductOwnerDTO updateProductOwnerDTO,
                                                   BindingResult bindingResult) {
        productOwnerService.updateProductOwner(id, updateProductOwnerDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProductOwner(@RequestBody List<Long> productOwnerIdList) {
        productOwnerService.deleteProductOwner(productOwnerIdList);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public GetProductOwnerByIdResponseDTO getProductOwnerById(@PathVariable Long id) {
        return productOwnerService.getProductOwnerById(id);
    }

    //todo pageSize? pageNumber?
    @GetMapping
    public GetProductOwnersResponseDTO getProductOwners(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) Integer pageSize,
                                                        @RequestParam(required = false) Integer pageNumber) {
        return productOwnerService.getProductOwners(name, pageSize, pageNumber);
    }

}
