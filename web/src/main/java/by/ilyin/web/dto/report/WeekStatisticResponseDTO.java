package by.ilyin.web.dto.report;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeekStatisticResponseDTO {

    private LocalDate startWeekDate;
    private LocalDate endWeekDate;
    private Float consumption;
    private Float income;
    private Float profit;

}
