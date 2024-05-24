package by.ilyin.core.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WeekStatisticResponseDTO {

    private LocalDate startWeekDate;
    private LocalDate endWeekDate;
    private Float consumption;
    private Float income;
    private Float profit;

}
