package by.ilyin.web.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Waybill {

    private Long id;
    private Integer distance;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Checkpoint> checkpoints;
    private Invoice invoice;
    private Car car;
    private CustomUser verifier;

    public enum Status {
        STARTED_CARRIAGE,
        FINISHED_CARRIAGE
    }

}
