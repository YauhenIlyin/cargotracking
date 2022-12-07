package by.ilyin.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetCarsResponseDTO {

    private Long totalElements;
    private List<GetCarByIdResponseDTO> content;

}
