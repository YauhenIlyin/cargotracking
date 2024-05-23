package by.ilyin.web.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Checkpoint {

    private Long id;
    private String address;
    private LocalDateTime requiredArrivalDate;
    private LocalDateTime checkpointDate;

}
