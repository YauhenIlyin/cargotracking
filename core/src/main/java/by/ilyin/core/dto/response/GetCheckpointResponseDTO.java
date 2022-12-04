package by.ilyin.core.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetCheckpointResponseDTO {

    private String address;
    private LocalDateTime requiredArrivalDate;
    private LocalDateTime checkpointDate;

}
