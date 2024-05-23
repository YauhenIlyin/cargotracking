package by.ilyin.web.service;

import by.ilyin.web.dto.InvoiceDTO;
import by.ilyin.web.dto.mapper.InvoiceDTOMapper;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.request.UpdateInvoiceDTO;
import by.ilyin.web.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.web.dto.response.GetInvoiceByIdResponseDTO;
import by.ilyin.web.dto.response.GetInvoicesResponseDTO;
import by.ilyin.web.entity.Invoice;
import by.ilyin.web.feign.InvoiceCoreFeignClient;
import by.ilyin.web.util.validator.CustomBindingResultValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceCoreFeignClient invoiceCoreFeignClient;
    private final CustomBindingResultValidator bindingResultValidator;
    private final InvoiceDTOMapper invoiceDTOMapper;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.address}")
    private String serverAddress;

    public CreateInvoiceResponseDTO createInvoice(InvoiceDTO invoiceDTO, BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        CreateInvoiceResponseDTO createInvoiceResponseDTO = invoiceCoreFeignClient.createInvoice(invoiceDTO);
        StringBuilder currentUrlSB = new StringBuilder();
        currentUrlSB
                .append("http://")
                .append(serverAddress)
                .append(":")
                .append(serverPort)
                .append(createInvoiceResponseDTO.getLocation());
        createInvoiceResponseDTO.setLocation(currentUrlSB.toString());
        return createInvoiceResponseDTO;
    }

    public void updateInvoiceById(Long invoiceId,
                                  UpdateInvoiceDTO updateInvoiceDTO,
                                  BindingResult bindingResult) {
        bindingResultValidator.validationProcess(bindingResult);
        invoiceCoreFeignClient.updateInvoiceById(invoiceId, updateInvoiceDTO);
    }

    public void deleteInvoices(List<Long> invoiceIdList) {
        invoiceCoreFeignClient.deleteInvoices(invoiceIdList);
    }

    public GetInvoiceByIdResponseDTO getInvoiceById(Long invoiceId) {
        return invoiceDTOMapper.mapToDto(invoiceCoreFeignClient.getInvoiceById(invoiceId));
    }

    public GetInvoicesResponseDTO getInvoices(Integer pageSize,
                                              Integer pageNumber,
                                              String number,
                                              String beforeCreationDate,
                                              String afterCreationDate,
                                              String beforeVerifiedDate,
                                              String afterVerifiedDate,
                                              Set<String> statuses) {
        PageDTO<Invoice> pageDTO = invoiceCoreFeignClient.getInvoices(
                pageSize,
                pageNumber,
                number,
                beforeCreationDate,
                afterCreationDate,
                beforeVerifiedDate,
                afterVerifiedDate,
                statuses);
        return new GetInvoicesResponseDTO(
                pageDTO.getTotalElements(),
                pageDTO.getContent()
                        .stream()
                        .map(invoiceDTOMapper::mapToDto)
                        .collect(Collectors.toList()));
    }

}
