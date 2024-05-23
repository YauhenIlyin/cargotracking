package by.ilyin.web.feign;

import by.ilyin.web.dto.ProductWriteOffDTO;
import by.ilyin.web.dto.request.UpdateProductWriteOffDTO;
import by.ilyin.web.entity.ProductWriteOff;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "productWriteoffsCoreFeignClient", url = "${feign.client.core.url}")
public interface ProductWriteOffCoreFeignClient {

    @PostMapping(value = "/api/product-writeoffs", consumes = "application/json")
    void createProductWriteOff(ProductWriteOffDTO productWriteOffDTO);

    @PutMapping(value = "/api/product-writeoffs/{id}", consumes = "application/json")
    void updateProductWriteOff(@PathVariable Long id,
                               UpdateProductWriteOffDTO updateProductWriteOffDTO);

    @DeleteMapping(value = "/api/product-writeoffs", consumes = "application/json")
    void deleteProductWriteOffs(List<Long> productWriteOffIdList);

    @GetMapping(value = "/api/product-writeoffs")
    List<ProductWriteOff> getProductWriteOffsByInvoiceId(@RequestParam Long invoiceId);

}
