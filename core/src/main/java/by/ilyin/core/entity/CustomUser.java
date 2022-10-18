package by.ilyin.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class CustomUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @Size(max = 20)
    private String name;

    @Column(name = "last_name")
    @NotEmpty
    @Size(max = 20)
    private String surname;

    @Column(name = "patronymic")
    @Size(max = 20)
    private String patronymic;

    @Column(name = "client_id")
    private long clientId;

    @Column(name = "born_date")
    private LocalDate bornDate;

    @Column(name = "email")
    @Email
    @Size(min = 1, max = 50)
    private String email;

    @Column(name = "town")
    @Size(max = 20)
    private String town;

    @Column(name = "street")
    @Size(max = 20)
    private String street;

    @Column(name = "house")
    @Size(max = 5)
    private String house;

    @Column(name = "flat")
    @Size(max = 5)
    private String flat;

    @Column(name = "login")
    @NotEmpty
    @Size(min = 5, max = 15)
    private String login;

    @Column(name = "user_password")
    @NotEmpty
    @Size(min = 5, max = 72)
    private String password;

    @Column(name = "passport_num")
    @Size(max = 30)
    private String passportNum;

    @Column(name = "issued_by")
    @Size(max = 50)
    private String issuedBy;

    @Size(min = 1)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private Set<UserRole> userRoles;

}