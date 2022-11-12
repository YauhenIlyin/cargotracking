package by.ilyin.core.dto.response;

import lombok.Data;

@Data
public class CreateUserResponseDTO {

    private String currentUserURI;

    public CreateUserResponseDTO(Long id) {
        this.currentUserURI = "/api/users/" + id;
    }

}
