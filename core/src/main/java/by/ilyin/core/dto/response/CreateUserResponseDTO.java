package by.ilyin.core.dto.response;

import lombok.Data;

@Data
public class CreateUserResponseDTO {

    private String currentUserURI;

    public CreateUserResponseDTO(long id) {
        this.currentUserURI = "/api/users/" + id;
    }

}
