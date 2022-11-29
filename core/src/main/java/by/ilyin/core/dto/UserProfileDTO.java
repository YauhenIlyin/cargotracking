package by.ilyin.core.dto;

import by.ilyin.core.entity.UserRole;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserProfileDTO {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate bornDate;
    private String town;
    private String street;
    private String house;
    private String flat;
    private String passportNum;
    private String issueBy;
    private String login;
    private String email;
    private Set<UserRole.UserRoleType> userRoles;

}
