package by.ilyin.core.controller;

import by.ilyin.core.dto.ProductOwnerDTO;
import by.ilyin.core.dto.request.UpdateProductOwnerDTO;
import by.ilyin.core.dto.response.CreateProductOwnerResponseDTO;
import by.ilyin.core.entity.ProductOwner;
import by.ilyin.core.service.ProductOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-owners")
@RequiredArgsConstructor
public class ProductOwnerCoreController {

    private final ProductOwnerService productOwnerService;

    @PostMapping
    public CreateProductOwnerResponseDTO createProductOwner(@RequestBody ProductOwnerDTO productOwnerDTO) {
        return productOwnerService.createProductOwner(productOwnerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductOwner(@PathVariable Long id,
                                                   @RequestBody UpdateProductOwnerDTO updateProductOwnerDTO) {
        productOwnerService.updateProductOwner(id, updateProductOwnerDTO);
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
    public ProductOwner getProductOwnerById(@PathVariable Long id) {
        return productOwnerService.getProductOwnerById(id);
    }

    @GetMapping
    public Page<ProductOwner> getProductOwners(@RequestParam(required = false) String name,
                                               Pageable pageable) {
        return productOwnerService.getProductOwners(name, pageable);
    }

}
