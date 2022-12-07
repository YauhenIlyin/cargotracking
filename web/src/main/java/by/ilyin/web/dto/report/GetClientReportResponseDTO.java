package by.ilyin.web.dto.report;

import lombok.Data;

import java.util.List;

@Data
public class GetClientReportResponseDTO {

    private List<WeekStatisticResponseDTO> statistic;
    private List<BestDriverResponseDTO> bestFiveDrivers;

}
