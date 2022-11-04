package by.ilyin.web.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CustomUser extends BaseEntity {

    private long id;
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
    private Set<UserRole> userRoles;

}
