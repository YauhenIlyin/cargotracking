package by.ilyin.core.service;

import by.ilyin.core.dto.ReachedCheckpointInfoDTO;
import by.ilyin.core.dto.WaybillDTO;
import by.ilyin.core.dto.mapper.WaybillDTOMapper;
import by.ilyin.core.dto.response.CreateWaybillResponseDTO;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaybillService {

    private final WaybillRepository waybillRepository;
    private final WaybillDTOMapper waybillDTOMapper;
    private final CarRepository carRepository;
    private final CustomUserRepository customUserRepository;
    private final InvoiceRepository invoiceRepository;
    private final CheckpointRepository checkpointRepository;
    private final @Qualifier("waybillFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;

    @Transactional
    public CreateWaybillResponseDTO createWaybill(WaybillDTO waybillDTO) {
        Waybill waybill = waybillDTOMapper.mapFromDTO(waybillDTO);
        Car car = (Car) validateAndGetResourceById(carRepository, waybillDTO.getCarId(), "Car");
        CustomUser verifier = (CustomUser) validateAndGetResourceById(customUserRepository,
                waybillDTO.getVerifierId(), "User-verifier");
        Invoice invoice = (Invoice) validateAndGetResourceById(invoiceRepository,
                waybillDTO.getInvoiceId(), "Invoice");
        waybill.setCar(car);
        waybill.setInvoice(invoice);
        waybill.setVerifier(verifier);
        Waybill realWaybill = waybillRepository.save(waybill);
        realWaybill.setCheckpoints(waybill.getCheckpoints()
                .stream()
                .peek(o -> o.setWaybill(realWaybill))
                .collect(Collectors.toList()));
        waybillRepository.save(realWaybill);
        return new CreateWaybillResponseDTO(realWaybill.getId());
    }

    public Page<Waybill> getWaybills(Pageable pageable, Set<String> carriageStatuses) {
        Map<String, Object> filterValues = new HashMap<>();
        if (carriageStatuses != null) {
            filterValues.put(
                    "carriageStatuses",
                    Arrays.stream(Waybill.Status.values())
                            .map(Enum::name)
                            .filter(carriageStatuses::contains)
                            .map(Waybill.Status::valueOf)
                            .collect(Collectors.toSet()));
        }
        Specification<Waybill> specification = takeGetInvoicesSpecification(filterValues);
        return waybillRepository.findAll(specification, pageable);
    }

    @Transactional
    public ReachedCheckpointInfoDTO reachCheckpoint(Long id) {
        validateAndGetResourceById(waybillRepository, id, "Waybill");
        Checkpoint checkpoint = checkpointRepository.findFirstByWaybillIdAndCheckpointDate(id, null);
        LocalDateTime reachCheckpointDate = LocalDateTime.now();
        checkpoint.setCheckpointDate(reachCheckpointDate);
        checkpointRepository.save(checkpoint);
        return new ReachedCheckpointInfoDTO(reachCheckpointDate, checkpoint.getAddress());
    }

    public List<Checkpoint> getCheckpoints(Long waybillId) {
        Waybill waybill = (Waybill) validateAndGetResourceById(waybillRepository, waybillId, "Waybill");
        return waybill.getCheckpoints();
    }

    private Specification<Waybill> takeGetInvoicesSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<Waybill> waybillFiltrationBuilder = new FiltrationBuilder<>();
        Set<Waybill.Status> waybillStatuses = (Set<Waybill.Status>) filterValues.get("carriageStatuses");
        if (waybillStatuses != null) {
            for (Waybill.Status currentStatus : waybillStatuses) {
                waybillFiltrationBuilder.addCriteria(
                        currentStatus != null,
                        "status",
                        KeyWords.FILTER_OPERATION_EQUALS,
                        currentStatus);
            }
        }
        return waybillFiltrationBuilder.build(fieldCriteriaTypes);
    }

    private BaseEntity validateAndGetResourceById(JpaRepository<? extends BaseEntity, Long> repository, Long id, String subject) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(subject + " with id " + id + " not found."));
    }


}
