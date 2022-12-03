package by.ilyin.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "checkpoints")
public class Checkpoint extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private LocalDateTime requiredArrivalDate;
    private LocalDateTime checkpointDate;
    @ManyToOne
    @JoinColumn(name = "waybill_id", referencedColumnName = "id")
    private Waybill waybill;

}
