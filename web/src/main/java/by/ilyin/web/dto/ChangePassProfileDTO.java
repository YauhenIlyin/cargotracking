package by.ilyin.web.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ChangePassProfileDTO {

    @Size(min = 5, max = 25, message = "Password length must be between 5 and 25 characters")
    private String oldPassword;
    @Size(min = 5, max = 25, message = "Password length must be between 5 and 25 characters")
    private String newPassword;

}
