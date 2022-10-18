package by.ilyin.core.dto.request;

import by.ilyin.core.entity.UserRole;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateUserRequestDTO {

    private long id;

    @Size(max = 20, message = "Name must be no more than 20 characters long")
    private String name;

    @NotEmpty
    @Size(max = 20, message = "Surname must be no more than 20 characters long")
    private String surname;

    @Size(max = 20, message = "Patronymic must be no more than 20 characters long")
    private String patronymic;

    private long clientId;

    private LocalDate bornDate;

    @Email(message = "String doesn't look like email")
    @Size(max = 50, message = "Email must be no more than 50 characters long")
    private String email;

    @Size(max = 20, message = "Town must be no more than 20 characters long")
    private String town;

    @Size(max = 20, message = "Street must be no more than 20 characters long")
    private String street;

    @Size(max = 5, message = "House name must be no more than 5 characters long")
    private String house;

    @Size(max = 5, message = "Name must be no more than 5 characters long")
    private String flat;

    @NotEmpty
    @Size(min = 5, max = 15, message = "Login length must be between 5 and 15 characters")
    private String login;

    @NotEmpty
    @Size(min = 5, max = 72, message = "Password length must be between 5 and 72 characters")
    private String password;

    @Size(max = 30, message = "Passport number must be no more than 30 characters long")
    private String passportNum;

    @Size(max = 50, message = "Issued by field must be no more than 50 characters long")
    private String issuedBy;

    @Size(min = 1, message = "User roles count must not be less than 1")
    private List<UserRole.UserRoleType> userRoles;

    @NotEmpty
    private boolean isChangePassword;

}
