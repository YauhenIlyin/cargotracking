package by.ilyin.web.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserRole {

    private Long id;
    private UserRoleType roleType;

    public enum UserRoleType {
        SYS_ADMIN,
        ADMIN,
        DISPATCHER,
        MANAGER,
        DRIVER,
        COMPANY_OWNER
    }

}
