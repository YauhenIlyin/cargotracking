package by.ilyin.core.dto.response;

import lombok.Data;

@Data
public class CreateCarResponseDTO {

    private String location;

    public CreateCarResponseDTO(Long id) {
        this.location = "/api/users/" + id;
    }

}
