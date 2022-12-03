package by.ilyin.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "waybills")
public class Waybill extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "distance")
    private Integer distance;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @OneToMany(mappedBy = "waybill", cascade = {CascadeType.PERSIST})
    private List<Checkpoint> checkpoints;
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @OneToOne
    @JoinColumn(name = "verifier_id")
    private CustomUser verifier;

    public enum Status {
        STARTED_CARRIAGE,
        FINISHED_CARRIAGE
    }

}
