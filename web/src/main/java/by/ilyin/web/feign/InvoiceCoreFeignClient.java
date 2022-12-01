package by.ilyin.web.feign;

import by.ilyin.web.dto.InvoiceDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateInvoiceDTO;
import by.ilyin.web.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.web.entity.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name = "invoicesCoreFeignClient", url = "${feign.client.core.url}")
public interface InvoiceCoreFeignClient {

    @PostMapping(value = "/api/invoices", consumes = "application/json")
    CreateInvoiceResponseDTO createInvoice(InvoiceDTO invoiceDTO);

    @PutMapping(value = "/api/invoices/{id}", consumes = "application/json")
    void updateInvoiceById(@PathVariable Long id, UpdateInvoiceDTO updateInvoiceDTO);

    @DeleteMapping(value = "/api/invoices", consumes = "application/json")
    void deleteInvoices(List<Long> invoiceIdList);

    @GetMapping(value = "/api/invoices/{id}")
    Invoice getInvoiceById(@PathVariable Long id);

    @GetMapping(value = "/api/invoices")
    PageDTO<Invoice> getInvoices(@RequestParam(required = false) Integer size,
                                 @RequestParam(required = false) Integer page,
                                 @RequestParam(required = false) String number,
                                 @RequestParam(required = false) String beforeCreationDate,
                                 @RequestParam(required = false) String afterCreationDate,
                                 @RequestParam(required = false) String beforeVerifiedDate,
                                 @RequestParam(required = false) String afterVerifiedDate,
                                 @RequestParam(required = false) Set<String> statuses);

}
