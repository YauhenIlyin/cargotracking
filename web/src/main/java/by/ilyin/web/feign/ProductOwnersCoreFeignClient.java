package by.ilyin.web.feign;

import by.ilyin.web.dto.ProductOwnerDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateProductOwnerDTO;
import by.ilyin.web.dto.response.CreateProductOwnerResponseDTO;
import by.ilyin.web.entity.ProductOwner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "productOwnersCoreFeignClient", url = "${feign.client.core.url}")
public interface ProductOwnersCoreFeignClient {

    @PostMapping(value = "/api/product-owners", consumes = "application/json")
    CreateProductOwnerResponseDTO createProductOwner(ProductOwnerDTO productOwnerDTO);

    @PutMapping(value = "/api/product-owners/{id}", consumes = "application/json")
    void updateProductOwner(@PathVariable Long id,
                            UpdateProductOwnerDTO updateProductOwnerDTO);

    @DeleteMapping(value = "/api/product-owners", consumes = "application/json")
    void deleteProductOwner(List<Long> productOwnerIdList);

    @GetMapping(value = "/api/product-owners/{id}", consumes = "application/json")
    ProductOwner getProductOwnerById(@PathVariable Long id);

    @GetMapping(value = "/api/product-owners", consumes = "application/json")
    PageDTO<ProductOwner> getProductOwners(@RequestParam String name,
                                           @RequestParam Integer size,
                                           @RequestParam Integer page);
}
