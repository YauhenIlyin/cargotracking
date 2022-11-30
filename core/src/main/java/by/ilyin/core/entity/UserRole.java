package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private UserRoleType roleType;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "userRoles")
    private List<CustomUser> users;

    public enum UserRoleType {
        SYS_ADMIN,
        ADMIN,
        DISPATCHER,
        MANAGER,
        DRIVER,
        COMPANY_OWNER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        if (!Objects.equals(id, userRole.id)) {
            return false;
        }
        return this.roleType == userRole.roleType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        return result;
    }

}
