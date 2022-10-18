package by.ilyin.core.dto.request;

import by.ilyin.core.entity.UserRole;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUsersRequestDTO {

    private String name;
    private String surname;
    private String patronymic;
    private LocalDate beforeBornDate;
    private LocalDate afterBornDate;
    private String town;
    private String street;
    private String house;
    private String flat;
    private List<UserRole.UserRoleType> userRoles;
    private int pageSize;
    private int pageNumber;

}
