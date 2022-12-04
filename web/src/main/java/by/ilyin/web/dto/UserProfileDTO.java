package by.ilyin.web.dto;

import by.ilyin.web.entity.UserRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserProfileDTO {

    private Long id;
    @Size(max = 20, message = "Name must be no more than 20 characters long")
    private String name;
    @NotEmpty
    @Size(max = 20, message = "Surname must be no more than 20 characters long")
    private String surname;
    @Size(max = 20, message = "Patronymic must be no more than 20 characters long")
    private String patronymic;
    private LocalDate bornDate;
    @Size(max = 20, message = "Town must be no more than 20 characters long")
    private String town;
    @Size(max = 20, message = "Street must be no more than 20 characters long")
    private String street;
    @Size(max = 5, message = "House name must be no more than 5 characters long")
    private String house;
    @Size(max = 5, message = "Name must be no more than 5 characters long")
    private String flat;
    @Size(max = 30, message = "Passport number must be no more than 30 characters long")
    private String passportNum;
    @Size(max = 50, message = "Issued by field must be no more than 50 characters long")
    private String issueBy;
    @NotEmpty
    @Size(min = 5, max = 15, message = "Login length must be between 5 and 15 characters")
    private String login;
    @Email(message = "String doesn't look like email")
    @Size(max = 50, message = "Email must be no more than 50 characters long")
    private String email;
    private Set<UserRole.UserRoleType> userRoles;

}
