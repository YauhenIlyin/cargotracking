package by.ilyin.web.dto.auth;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SignInDTO {

    @Size(min = 5, max = 15, message = "Login length must be between 5 and 15 characters")
    private String login;
    @Size(min = 5, max = 25, message = "Password length must be between 5 and 25 characters")
    private String password;

}
