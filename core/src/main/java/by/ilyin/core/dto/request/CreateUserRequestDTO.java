package by.ilyin.core.dto.request;

import by.ilyin.core.entity.UserRole;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor //todo
@EqualsAndHashCode
@ToString
public class CreateUserRequestDTO {

    private String name;
    private String surname;
    private String patronymic;
    private long clientId;
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
    private List<UserRole.UserRoleType> userRoles;

}