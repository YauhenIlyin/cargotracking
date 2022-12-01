package by.ilyin.web.controller;

import by.ilyin.web.dto.InvoiceDTO;
import by.ilyin.web.dto.request.UpdateInvoiceDTO;
import by.ilyin.web.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.web.dto.response.GetInvoiceByIdResponseDTO;
import by.ilyin.web.dto.response.GetInvoicesResponseDTO;
import by.ilyin.web.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceWebController {

    private final InvoiceService invoiceService;

    @PostMapping
    public CreateInvoiceResponseDTO createInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO,
                                                  BindingResult bindingResult) {
        return invoiceService.createInvoice(invoiceDTO, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInvoice(@PathVariable Long id,
                                              @RequestBody @Valid UpdateInvoiceDTO updateInvoiceDTO,
                                              BindingResult bindingResult) {
        invoiceService.updateInvoiceById(id, updateInvoiceDTO, bindingResult);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInvoices(@RequestBody List<Long> invoiceIdList) {
        invoiceService.deleteInvoices(invoiceIdList);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public GetInvoiceByIdResponseDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping
    public GetInvoicesResponseDTO getInvoices(@RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer pageNumber,
                                              @RequestParam(required = false) String number,
                                              @RequestParam(required = false) String beforeCreationDate,
                                              @RequestParam(required = false) String afterCreationDate,
                                              @RequestParam(required = false) String beforeVerifiedDate,
                                              @RequestParam(required = false) String afterVerifiedDate,
                                              @RequestParam(required = false) Set<String> statuses) {
        return invoiceService.getInvoices(
                pageSize,
                pageNumber,
                number,
                beforeCreationDate,
                afterCreationDate,
                beforeVerifiedDate,
                afterVerifiedDate,
                statuses);
    }

}
