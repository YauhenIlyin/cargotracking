package by.ilyin.core.service;

import by.ilyin.core.dto.report.BestDriverResponseDTO;
import by.ilyin.core.dto.report.GetClientReportResponseDTO;
import by.ilyin.core.dto.report.WeekStatisticResponseDTO;
import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.entity.Invoice;
import by.ilyin.core.entity.ProductWriteOff;
import by.ilyin.core.evidence.KeyWords;
import by.ilyin.core.repository.InvoiceRepository;
import by.ilyin.core.repository.filtration.FiltrationBuilder;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final InvoiceRepository invoiceRepository;
    private final @Qualifier("invoiceFieldCriteriaTypesImpl") FieldCriteriaTypes fieldCriteriaTypes;
    //todo how to get  the price ?
    private final int FUEL_INCOME_COEFFICIENT = 50;
    private final int CONSUMPTION_UNIT_AMOUNT_COEFFICIENT = 3;

    public GetClientReportResponseDTO getClientReport(Long clientId, String initialDate, String finalDate) {
        LocalDate initialLocalDate = null;
        LocalDate finalLocalDate = null;
        if (initialDate != null) {
            initialLocalDate = LocalDate.parse(initialDate);
        }
        if (finalDate != null) {
            finalLocalDate = LocalDate.parse(finalDate);
        }
        List<Invoice> invoiceList = findClientInvoicesByPeriod(clientId, initialLocalDate, finalLocalDate);
        List<WeekStatisticResponseDTO> statistic = new ArrayList<>();
        List<BestDriverResponseDTO> bestDriverList = new ArrayList<>();
        if (invoiceList.size() > 0) {
            Map<CustomUser, Float> driverProfitMap = new HashMap<>();
            if (initialLocalDate == null) {
                initialLocalDate = invoiceList.get(0).getCreationDate();
            }
            int size = invoiceList.size();
            for (int index = 0; index < size; ++index) {
                float currentWeekIncome = 0.f;
                float currentWeekConsumption = 0.f;
                LocalDate currentFinalDate = initialLocalDate.plusDays(7);
                while (index < size) {
                    Invoice invoiceContainer = invoiceList.get(index);
                    if (initialLocalDate.compareTo(invoiceContainer.getCreationDate()) <= 0 &&
                            currentFinalDate.compareTo(invoiceContainer.getVerificationDate()) >= 0) {
                        Map<String, Float> calculatedMap = calculateInvoice(invoiceContainer);
                        currentWeekIncome += calculatedMap.get("income");
                        currentWeekConsumption += calculatedMap.get("consumption");
                        CustomUser driver = invoiceContainer.getDriver();
                        Float driverProfit = Optional.ofNullable(driverProfitMap.get(driver)).orElse(0.f);
                        driverProfit += calculatedMap.get("profit");
                        driverProfitMap.put(driver, driverProfit);
                        ++index;
                    } else {
                        statistic.add(
                                new WeekStatisticResponseDTO(
                                        initialLocalDate,
                                        currentFinalDate,
                                        currentWeekConsumption,
                                        currentWeekIncome,
                                        currentWeekIncome - currentWeekConsumption));
                        initialLocalDate = initialLocalDate.plusDays(7);
                        break;
                    }
                }
            }
            SortedMap<Float, CustomUser> sortedDriverProfitMap = new TreeMap<>();
            for (CustomUser key : driverProfitMap.keySet()) {
                sortedDriverProfitMap.put(driverProfitMap.get(key), key);
            }
            int count = 0;
            while (sortedDriverProfitMap.size() > 0 && count <= 5) {
                Float keyFloatProfit = sortedDriverProfitMap.lastKey();
                CustomUser driver = sortedDriverProfitMap.get(keyFloatProfit);
                BestDriverResponseDTO currentBestDriverResponseDTO = new BestDriverResponseDTO(
                        new StringBuilder()
                                .append(driver.getName())
                                .append(" ")
                                .append(driver.getSurname())
                                .append(" ")
                                .append(driver.getPatronymic())
                                .toString(),
                        keyFloatProfit
                );
                bestDriverList.add(currentBestDriverResponseDTO);
                sortedDriverProfitMap.remove(keyFloatProfit);
                ++count;
            }
        }
        return new GetClientReportResponseDTO(statistic, bestDriverList);
    }

    private Map<String, Float> calculateInvoice(Invoice invoice) {
        Map<String, Float> invoiceCalculatedMap = new HashMap<>(3);
        float income;
        float consumption = 0.f;
        float profit;
        Float fuelConsumption = invoice.getWaybill().getCar().getFuelConsumption();
        Integer distance = invoice.getWaybill().getDistance();
        income = ((distance / 100.f) * fuelConsumption) * FUEL_INCOME_COEFFICIENT;
        for (ProductWriteOff current : invoice.getProductWriteOffs()) {
            consumption += current.getAmount() * CONSUMPTION_UNIT_AMOUNT_COEFFICIENT;
        }
        profit = income - consumption;
        invoiceCalculatedMap.put("income", income);
        invoiceCalculatedMap.put("consumption", consumption);
        invoiceCalculatedMap.put("profit", profit);
        return invoiceCalculatedMap;
    }

    private List<Invoice> findClientInvoicesByPeriod(Long clientId, LocalDate initialDate, LocalDate finalDate) {
        Map<String, Object> filterValues = new HashMap<>();
        if (initialDate != null) {
            filterValues.put("initialDate", initialDate);
        }
        if (finalDate != null) {
            filterValues.put("finalDate", finalDate);
        }
        Specification<Invoice> specification = takeGetInvoicesSpecification(filterValues);
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.asc("creationDate"));
        return invoiceRepository
                .findAll(specification, Sort.by(orders))
                .stream()
                .filter((o) -> o.getProductOwner().getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    private Specification<Invoice> takeGetInvoicesSpecification(Map<String, Object> filterValues) {
        FiltrationBuilder<Invoice> invoiceFiltrationBuilder = new FiltrationBuilder<>();
        invoiceFiltrationBuilder
                .addCriteria(
                        filterValues.get("initialDate") != null,
                        "creationDate",
                        KeyWords.FILTER_OPERATION_MORE,
                        filterValues.get("initialDate"))
                .addCriteria(
                        filterValues.get("finalDate") != null,
                        "verificationDate",
                        KeyWords.FILTER_OPERATION_LESS,
                        filterValues.get("finalDate"));
        return invoiceFiltrationBuilder.build(fieldCriteriaTypes);
    }

}
