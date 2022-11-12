package by.ilyin.core.dto.request;

import by.ilyin.core.entity.UserRole;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UpdateUserRequestDTO {

    private String name;
    private String surname;
    private String patronymic;
    private Long clientId;
    private LocalDate bornDate;
    private String email;
    private String town;
    private String street;
    private String house;
    private String flat;
    private String login;
    private String password;
    private String passportNum;
    private String issuedBy;
    private Set<UserRole.UserRoleType> userRoles;
    private Boolean isChangePassword;

}
