package by.ilyin.core.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class GetStatisticSysadminResponseDTO {

    private LocalDate startIntervalDate;
    private LocalDate endIntervalDate;
    private Float profit;
    private Float income;
    private Float consumption;
    private Integer activeClients;
    private Integer lostClients;

}
