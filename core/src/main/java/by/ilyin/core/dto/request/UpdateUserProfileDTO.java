package by.ilyin.core.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserProfileDTO {

    private String name;
    private String surname;
    private String patronymic;
    private LocalDate bornDate;
    private String town;
    private String street;
    private String house;
    private String flat;

}
