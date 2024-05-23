package by.ilyin.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetProductOwnersResponseDTO {

    private List<GetProductOwnerByIdResponseDTO> content;
    private Long totalElements;

}
