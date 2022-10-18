package by.ilyin.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    private long id;
    private UserRoleType userRole;

    public enum UserRoleType {
        SYS_ADMIN,
        ADMIN,
        DISPATCHER,
        MANAGER,
        DRIVER,
        COMPANY_OWNER;
    }

}