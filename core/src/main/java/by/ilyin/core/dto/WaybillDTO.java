package by.ilyin.core.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WaybillDTO {

    private Long invoiceId;
    private Integer distance;
    private Long carId;
    private LocalDateTime endDate;
    private Long verifierId;
    private List<CheckpointDTO> checkpoints;

}
