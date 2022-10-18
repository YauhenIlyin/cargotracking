package by.ilyin.core.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseDTO {

    private String currentUserURI;

    public CreateUserResponseDTO(long id) {
        this.currentUserURI = "api/users/{" + id + "}"; //todo ask Egor how to store "current uri"
    }

}