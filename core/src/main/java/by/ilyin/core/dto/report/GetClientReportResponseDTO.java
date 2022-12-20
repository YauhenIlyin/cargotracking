package by.ilyin.core.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetClientReportResponseDTO {

    private List<WeekStatisticResponseDTO> statistic;
    private List<BestDriverResponseDTO> bestFiveDrivers;

}
