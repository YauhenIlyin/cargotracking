package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "storage_id")
    private Long storageId;
    @Column(name = "product_owner_id")
    private Long productOwnerId;
    @Column(name = "creator_id")
    private Long creatorId;
    @Column(name = "driver_id")
    private Long driverId;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "verification_date")
    private LocalDate verificationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @OneToMany(mappedBy = "invoice", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Storage storage;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "product_owner_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductOwner productOwner;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CustomUser creator;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CustomUser driver;

    public enum Status {
        MADE_OUT,
        DELIVERED,
        VERIFICATION_COMPLETE
    }

}
