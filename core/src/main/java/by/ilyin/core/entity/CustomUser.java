package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class CustomUser extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "born_date")
    private LocalDate bornDate;
    @Column(name = "email")
    private String email;
    @Column(name = "town")
    private String town;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "flat")
    private String flat;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "user_password")
    private String password;
    @Column(name = "passport_num")
    private String passportNum;
    @Column(name = "issued_by")
    private String issuedBy;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @Size(min = 1)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private Set<UserRole> userRoles;
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "generalAdmin")
    private Client client1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUser that = (CustomUser) o;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(surname, that.surname)) return false;
        if (!Objects.equals(patronymic, that.patronymic)) return false;
        if (!Objects.equals(bornDate, that.bornDate)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(town, that.town)) return false;
        if (!Objects.equals(street, that.street)) return false;
        if (!Objects.equals(house, that.house)) return false;
        if (!Objects.equals(flat, that.flat)) return false;
        if (!Objects.equals(login, that.login)) return false;
        if (!Objects.equals(password, that.password)) return false;
        if (!Objects.equals(passportNum, that.passportNum)) return false;
        if (!Objects.equals(issuedBy, that.issuedBy)) return false;
        if (!Objects.equals(userRoles, that.userRoles)) return false;
        return Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (bornDate != null ? bornDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passportNum != null ? passportNum.hashCode() : 0);
        result = 31 * result + (issuedBy != null ? issuedBy.hashCode() : 0);
        result = 31 * result + (userRoles != null ? userRoles.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomUser{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", bornDate=").append(bornDate);
        sb.append(", email='").append(email).append('\'');
        sb.append(", town='").append(town).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", house='").append(house).append('\'');
        sb.append(", flat='").append(flat).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", passportNum='").append(passportNum).append('\'');
        sb.append(", issuedBy='").append(issuedBy).append('\'');
        sb.append(", userRoles=").append(userRoles);
        sb.append(", clientId=");
        sb.append(client != null ? client.getId().toString() : "null").append('}');
        return sb.toString();
    }

}
