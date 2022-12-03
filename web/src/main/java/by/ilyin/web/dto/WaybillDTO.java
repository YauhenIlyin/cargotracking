package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class WaybillDTO {

    @NotNull
    private Long invoiceId;
    @NotNull
    private Integer distance;
    @NotNull
    private Long carId;
    @NotNull
    private LocalDateTime endDate;
    @NotNull
    private Long verifierId;
    @NotNull
    @Size(min = 1)
    private List<CheckpointDTO> checkpoints;

}
