package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "client_name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ClientStatus status;
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private CustomUser generalAdmin;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<CustomUser> customUsers;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "client")
    private HappyBirthdayTemplate happyBirthdayTemplate;
    @OneToMany(mappedBy = "client")
    private List<Storage> storages;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<ProductOwner> productOwners;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<Car> cars;

    public enum ClientStatus {
        PRIVATE,
        LEGAL
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        if (!Objects.equals(id, client.id)) return false;
        if (!Objects.equals(name, client.name)) return false;
        if (status != client.status) return false;
        if (!Objects.equals(deleteDate, client.deleteDate)) return false;
        return Objects.equals(generalAdmin.getId(), client.generalAdmin.getId());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (deleteDate != null ? deleteDate.hashCode() : 0);
        result = 31 * result + (generalAdmin != null ? generalAdmin.getId().hashCode() : 0);
        return result;
    }

}
