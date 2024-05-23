package by.ilyin.core.controller;

import by.ilyin.core.dto.InvoiceDTO;
import by.ilyin.core.dto.request.UpdateInvoiceDTO;
import by.ilyin.core.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.core.entity.Invoice;
import by.ilyin.core.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceCoreController {

    private final InvoiceService invoiceService;

    @PostMapping
    CreateInvoiceResponseDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoice(invoiceDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateInvoice(@PathVariable Long id,
                                       @RequestBody UpdateInvoiceDTO updateInvoiceDTO) {
        invoiceService.updateInvoice(id, updateInvoiceDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    //todo in task /{id}
    @DeleteMapping
    ResponseEntity<Void> deleteInvoices(@RequestBody List<Long> invoiceIdList) {
        invoiceService.deleteInvoices(invoiceIdList);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping
    public Page<Invoice> getInvoices(Pageable pageable,
                                     @RequestParam(required = false) String number,
                                     @RequestParam(required = false) String beforeCreationDate,
                                     @RequestParam(required = false) String afterCreationDate,
                                     @RequestParam(required = false) String beforeVerifiedDate,
                                     @RequestParam(required = false) String afterVerifiedDate,
                                     @RequestParam(required = false) Set<String> statuses) {
        return invoiceService.getInvoices(
                pageable,
                number,
                beforeCreationDate,
                afterCreationDate,
                beforeVerifiedDate,
                afterVerifiedDate,
                statuses);
    }

}
