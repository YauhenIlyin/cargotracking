package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "storage_name")
    private String name;
    @Column(name = "address")
    private String address;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Client client;

}
