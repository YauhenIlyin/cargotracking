package by.ilyin.core.dto.response;

import lombok.Data;

@Data
public class CreateClientResponseDTO {

    private String currentClientURI;

    public CreateClientResponseDTO(Long id) {
        this.currentClientURI = "/api/clients/" + id;
    }

}
