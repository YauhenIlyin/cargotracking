package by.ilyin.web.controller;

import by.ilyin.web.dto.ProductWriteOffDTO;
import by.ilyin.web.dto.request.UpdateProductWriteOffDTO;
import by.ilyin.web.dto.response.GetProductWriteOffResponseDTO;
import by.ilyin.web.service.ProductWriteOffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-writeoffs")
public class ProductWriteOffWebController {

    private final ProductWriteOffService productWriteOffService;

    @PostMapping
    public ResponseEntity<Void> createProductWriteOff(@RequestBody @Valid ProductWriteOffDTO productWriteOffDTO,
                                                      BindingResult bindingResult) {
        productWriteOffService.createProductWriteOff(productWriteOffDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductWriteOff(@PathVariable Long id,
                                                      @RequestBody UpdateProductWriteOffDTO updateProductWriteOffDTO,
                                                      BindingResult bindingResult) {
        productWriteOffService.updateProductWriteOff(id, updateProductWriteOffDTO, bindingResult);
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
    public List<GetProductWriteOffResponseDTO> getProductWriteOffsBiInvoiceId(@RequestParam Long invoiceId) {
        return productWriteOffService.getProductWriteOffsByInvoiceId(invoiceId);
    }

}
