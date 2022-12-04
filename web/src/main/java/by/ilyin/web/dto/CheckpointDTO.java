package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CheckpointDTO {

    @NotNull
    private String address;
    @NotNull
    private LocalDateTime requiredArrivalDate;

}
