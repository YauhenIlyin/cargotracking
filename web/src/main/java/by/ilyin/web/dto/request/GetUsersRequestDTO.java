package by.ilyin.web.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUsersRequestDTO {

    private String name;
    private String surname;
    private String patronymic;
    private String beforeBornDate;
    private String afterBornDate;
    private String town;
    private String street;
    private String house;
    private String flat;
    private String[] userRoles;
    private String pageSize;
    private String pageNumber;

}