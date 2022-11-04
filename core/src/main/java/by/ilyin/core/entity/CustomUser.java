package by.ilyin.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class CustomUser extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "client_id")
    private long clientId;

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

    @Column(name = "login")
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "passport_num")
    private String passportNum;

    @Column(name = "issued_by")
    private String issuedBy;

    @Size(min = 1)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private Set<UserRole> userRoles;

}
