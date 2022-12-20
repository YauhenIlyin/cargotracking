package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "waybill_id", referencedColumnName = "id")
    private Waybill waybill;

}
