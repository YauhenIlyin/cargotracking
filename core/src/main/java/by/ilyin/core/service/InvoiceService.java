package by.ilyin.core.service;

import by.ilyin.core.dto.InvoiceDTO;
import by.ilyin.core.dto.mapper.InvoiceDTOMapper;
import by.ilyin.core.dto.request.UpdateInvoiceDTO;
import by.ilyin.core.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.core.entity.*;
import by.ilyin.core.dto.request.UpdateProductDTO;
import by.ilyin.core.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.core.entity.*;
import by.ilyin.core.dto.request.UpdateProductDTO;
import by.ilyin.core.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.core.entity.*;
import by.ilyin.core.dto.request.UpdateProductDTO;
import by.ilyin.core.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.core.entity.*;
import by.ilyin.core.dto.request.UpdateProductDTO;
import by.ilyin.core.dto.response.CreateInvoiceResponseDTO;
import by.ilyin.core.entity.*;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.exception.http.client.ResourceNotFoundException;
import by.ilyin.core.repository.*;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final StorageRepository storageRepository;
    private final ProductOwnerRepository productOwnerRepository;
    private final ProductRepository productRepository;
    private final CustomUserRepository userRepository;
    private final InvoiceDTOMapper invoiceDTOMapper;
    private final @Qualifier("invoiceFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateInvoiceResponseDTO createInvoice(InvoiceDTO invoiceDTO) {
        Storage storage = (Storage) validateAndGetResourceById(storageRepository,
                invoiceDTO.getStorageId(), "Storage");
        ProductOwner productOwner = (ProductOwner) validateAndGetResourceById(productOwnerRepository,
                invoiceDTO.getProductOwnerId(), "ProductOwner");
        CustomUser userCreator = (CustomUser) validateAndGetResourceById(userRepository,
                invoiceDTO.getCreatorId(), "User-creator");
        CustomUser userDriver = (CustomUser) validateAndGetResourceById(userRepository,
                invoiceDTO.getDriverId(), "User-driver");
        Invoice currentInvoice = invoiceDTOMapper.mapFromDTO(invoiceDTO);
        currentInvoice.setStorage(storage);
        currentInvoice.setProductOwner(productOwner);
        currentInvoice.setCreator(userCreator);
        currentInvoice.setDriver(userDriver);
        currentInvoice.setCreationDate(LocalDate.now());
        List<Product> productList = currentInvoice.getProducts();
        currentInvoice.setProducts(null);
        final Invoice invoice = invoiceRepository.save(currentInvoice);
        currentInvoice.setProducts(
                productList
                        .stream()
                        .peek((o) -> o.setInvoice(invoice))
                        .collect(Collectors.toList()));
        invoiceRepository.save(currentInvoice);
        return new CreateInvoiceResponseDTO(invoice.getId());
    }

    @Transactional
    public void updateInvoice(Long invoiceId, UpdateInvoiceDTO updateInvoiceDTO) {
        Storage storage = (Storage) validateAndGetResourceById(storageRepository, updateInvoiceDTO.getStorageId(), "Storage");
        ProductOwner productOwner = (ProductOwner) validateAndGetResourceById(productOwnerRepository, updateInvoiceDTO.getProductOwnerId(), "ProductOwner");
        CustomUser userDriver = (CustomUser) validateAndGetResourceById(userRepository, updateInvoiceDTO.getDriverId(), "User-driver");
        Invoice invoice = (Invoice) validateAndGetResourceById(invoiceRepository, invoiceId, "Invoice");
        invoice.setNumber(updateInvoiceDTO.getNumber());
        invoice.setStorage(storage);
        invoice.setProductOwner(productOwner);
        invoice.setDriver(userDriver);
        List<Product> productList = updateInvoiceDTO.getProducts()
                .stream()
                .map((o) -> {
                    Product product = (Product) validateAndGetResourceById(
                            productRepository, o.getId(), "Product");
                    product.setName(o.getName());
                    product.setAmount(o.getAmount());
                    product.setInvoice(invoice);
                    return product;
                })
                .collect(Collectors.toList());
        invoice.setProducts(productList);
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoices(List<Long> invoiceIdList) {
        invoiceRepository.deleteByIdIsIn(invoiceIdList);
    }

    public Invoice getInvoiceById(Long invoiceId) {
        return (Invoice) validateAndGetResourceById(invoiceRepository, invoiceId, "Invoice");
    }

    public Page<Invoice> getInvoices(Pageable pageable,
                                     String number,
                                     String beforeCreationDate,
                                     String afterCreationDate,
                                     String beforeVerifiedDate,
                                     String afterVerifiedDate,
                                     Set<String> statuses) {
        Map<String, Object> filterValues = new HashMap<>();
        filterValues.put("number", number);
        if (beforeCreationDate != null) {
            filterValues.put("beforeCreationDate", LocalDate.parse(beforeCreationDate));
        }
        if (afterCreationDate != null) {
            filterValues.put("afterCreationDate", LocalDate.parse(afterCreationDate));
        }
        if (beforeVerifiedDate != null) {
            filterValues.put("beforeVerifiedDate", LocalDate.parse(beforeVerifiedDate));
        }
        if (afterVerifiedDate != null) {
            filterValues.put("afterVerifiedDate", LocalDate.parse(afterVerifiedDate));
        }
        if (statuses != null) {
            filterValues.put(
                    "statuses",
                    Arrays.stream(Invoice.Status.values())
                            .map(Enum::name)
                            .filter(statuses::contains)
                            .map(Invoice.Status::valueOf)
                            .collect(Collectors.toSet()));
        }
        Specification<Invoice> specification = takeGetInvoicesSpecification(filterValues);
        return invoiceRepository.findAll(specification, pageable);
    }

    private Specification<Invoice> takeGetInvoicesSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<Invoice> invoiceFiltrationBuilder = new FiltrationBuilder<>();
        invoiceFiltrationBuilder
                .addCriteria(
                        filterValues.get("number") != null,
                        "number",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        filterValues.get("number"))
                .addCriteria(
                        filterValues.get("beforeCreationDate") != null,
                        "creationDate",
                        KeyWords.FILTER_OPERATION_MORE,
                        filterValues.get("beforeCreationDate"))
                .addCriteria(
                        filterValues.get("afterCreationDate") != null,
                        "creationDate",
                        KeyWords.FILTER_OPERATION_LESS,
                        filterValues.get("afterCreationDate"))
                .addCriteria(
                        filterValues.get("beforeVerifiedDate") != null,
                        "verifiedDate",
                        KeyWords.FILTER_OPERATION_MORE,
                        filterValues.get("beforeVerifiedDate"))
                .addCriteria(
                        filterValues.get("afterVerifiedDate") != null,
                        "verifiedDate",
                        KeyWords.FILTER_OPERATION_LESS,
                        filterValues.get("afterVerifiedDate"));
        Set<Invoice.Status> invoiceStatuses = (Set<Invoice.Status>) filterValues.get("statuses");
        if (invoiceStatuses != null) {
            for (Invoice.Status currentStatus : invoiceStatuses) {
                invoiceFiltrationBuilder.addCriteria(
                        currentStatus != null,
                        "status",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        currentStatus);
            }
        }
        return invoiceFiltrationBuilder.build(fieldCriteriaTypes);
    }


    private BaseEntity validateAndGetResourceById(JpaRepository<? extends BaseEntity, Long> repository, Long id, String subject) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(subject + " with id " + id + " not found."));
    }

}
