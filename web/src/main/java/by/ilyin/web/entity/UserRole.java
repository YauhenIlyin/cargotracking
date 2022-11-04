package by.ilyin.web.entity;

import lombok.Data;

@Data
public class UserRole extends BaseEntity {

    private long id;
    private UserRoleType roleType;

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
        UserRole userRole1 = (UserRole) o;
        return roleType == userRole1.roleType;
    }

    @Override
    public int hashCode() {
        return roleType.hashCode();
    }

}
