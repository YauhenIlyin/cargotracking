package by.ilyin.web.dto;

import by.ilyin.web.entity.UserRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CustomUserDTO {

    @Size(max = 20)
    private String name;
    @NotEmpty
    @Size(max = 20)
    private String surname;
    @Size(max = 20)
    private String patronymic;
    private Long clientId;
    private LocalDate bornDate;
    @Email
    @Size(min = 1, max = 50)
    private String email;
    @Size(max = 20)
    private String town;
    @Size(max = 20)
    private String street;
    @Size(max = 5)
    private String house;
    @Size(max = 5)
    private String flat;
    @NotEmpty
    @Size(min = 5, max = 15)
    private String login;
    @NotEmpty
    @Size(min = 5, max = 72)
    private String password;
    @Size(max = 30)
    private String passportNum;
    @Size(max = 50)
    private String issuedBy;
    @Size(min = 1)
    private Set<UserRole.UserRoleType> userRoles;

}
