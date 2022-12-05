package by.ilyin.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetWaybillsResponseDTO {

    private Long totalElements;
    private List<GetWaybillResponseDTO> content;

}
