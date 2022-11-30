package by.ilyin.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductOwnerByIdResponseDTO {

    private Long id;
    private String name;
    private String address;

}
