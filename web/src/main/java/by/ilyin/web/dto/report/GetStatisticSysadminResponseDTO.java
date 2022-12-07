package by.ilyin.web.dto.report;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetStatisticSysadminResponseDTO {

    private LocalDate startIntervalDate;
    private LocalDate endIntervalDate;
    private Float profit;
    private Float income;
    private Float consumption;
    private Integer activeClients;
    private Integer lostClients;

}
