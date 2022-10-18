package by.ilyin.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private UserRoleType roleType;

    @ToString.Exclude
    @ManyToMany(mappedBy = "userRoles")
    private List<CustomUser> users;

    public enum UserRoleType {
        SYS_ADMIN,
        ADMIN,
        DISPATCHER,
        MANAGER,
        DRIVER,
        COMPANY_OWNER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole userRole1 = (UserRole) o;
        return roleType == userRole1.roleType;
    }

    @Override
    public int hashCode() {
        return roleType.hashCode();
    }

}