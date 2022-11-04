package by.ilyin.core.dto.response;

import by.ilyin.core.dto.CustomUserDTO;
import lombok.*;

import java.util.List;

@Data
public class GetUsersResponseDTO {

    private List<CustomUserDTO> content;
    private long totalElements;

}
