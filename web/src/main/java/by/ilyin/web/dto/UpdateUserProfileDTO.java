package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UpdateUserProfileDTO {

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

}
