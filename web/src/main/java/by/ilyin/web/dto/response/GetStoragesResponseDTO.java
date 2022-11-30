package by.ilyin.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetStoragesResponseDTO {

    private List<GetStorageByIdResponseDTO> content;
    private Long totalElements;

}
