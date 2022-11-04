package by.ilyin.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateUserResponseDTO {

    private String currentUserURI;

    public CreateUserResponseDTO(long id) {
        StringBuilder currentUrnSB = new StringBuilder();
        currentUrnSB
                .append("/api/users/{")
                .append(id)
                .append("}");
        this.currentUserURI = currentUrnSB.toString();
    }

}
