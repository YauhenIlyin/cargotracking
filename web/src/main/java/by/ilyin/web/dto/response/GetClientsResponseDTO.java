package by.ilyin.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetClientsResponseDTO {

    private List<GetClientByIdResponseDTO> content;
    private Long totalElements;

}
