package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "storages")
public class Storage extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "client_id")
//    private Long clientId;
    @Column(name = "storage_name")
    private String name;
    @Column(name = "address")
    private String address;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "storage")
    private List<Invoice> invoices;

}
