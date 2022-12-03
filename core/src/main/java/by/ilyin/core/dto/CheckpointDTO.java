package by.ilyin.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckpointDTO {

    private String address;
    private LocalDateTime requiredArrivalDate;

}
