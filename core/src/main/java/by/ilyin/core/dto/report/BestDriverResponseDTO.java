package by.ilyin.core.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestDriverResponseDTO {

    private String driverFullName;
    private Float profit;

}
