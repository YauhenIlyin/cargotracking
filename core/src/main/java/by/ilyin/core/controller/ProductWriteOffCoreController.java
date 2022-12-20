package by.ilyin.core.controller;

import by.ilyin.core.dto.ProductWriteOffDTO;
import by.ilyin.core.dto.request.UpdateProductWriteOffDTO;
import by.ilyin.core.entity.ProductWriteOff;
import by.ilyin.core.service.ProductWriteOffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-writeoffs")
public class ProductWriteOffCoreController {

    private final ProductWriteOffService productWriteOffService;

    @PostMapping
    public ResponseEntity<Void> createProductWriteOff(@RequestBody ProductWriteOffDTO productWriteOffDTO) {
        productWriteOffService.createProductWriteOff(productWriteOffDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductWriteOff(@PathVariable Long id,
                                                      @RequestBody UpdateProductWriteOffDTO updateProductWriteOffDTO) {
        productWriteOffService.updateProductWriteOff(id, updateProductWriteOffDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProductWriteOffs(@RequestBody List<Long> productWriteOffIdList) {
        productWriteOffService.deleteProductWriteOffs(productWriteOffIdList);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping
    public List<ProductWriteOff> getProductWriteOffsByInvoiceId(@RequestParam Long invoiceId) {
        return productWriteOffService.getProductWriteOffsByInvoiceId(invoiceId);
    }

}
